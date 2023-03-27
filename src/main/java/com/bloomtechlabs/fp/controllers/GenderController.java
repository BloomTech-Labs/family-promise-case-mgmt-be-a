package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.Gender;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.services.GenderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/genders")
public class GenderController {

	@Autowired
	private GenderService genderService;

	private final ObjectMapper mapper = new ObjectMapper();

	@GetMapping(value = "")
	public List<Gender> getAllGenders() {
		return this.genderService.getAllGenders();
	}

	@GetMapping(value = "{offset}/{limit}")
	public ResponseEntity<Page<Gender>> getAllGendersPaginated(
		@PathVariable(value = "offset") int offset,
		@PathVariable(value = "limit") int limit
	) {
		return ResponseEntity.ok(this.genderService.getAllGendersPaginated(offset, limit));
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<ObjectNode> getGenderById(@PathVariable(value = "id") UUID id) {
		ObjectNode json;
		Gender gender;

		try {
			gender = this.genderService.getGenderById(id);
		} catch(ResourceNotFoundException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not get gender!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		json = this.mapper.convertValue(gender, ObjectNode.class);

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@PostMapping(value = "")
	public ResponseEntity<ObjectNode> createGender(@RequestBody Gender gender) {
		try {
			this.genderService.createGender(gender);
		} catch(IllegalArgumentException e) {
			ObjectNode json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not create gender!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(value = "")
	public ResponseEntity<ObjectNode> updateGender(@RequestBody Gender gender) {
		ObjectNode json = this.mapper.createObjectNode();
		Gender updatedGender;

		try {
			updatedGender = this.genderService.updateGender(gender);
		} catch(IllegalArgumentException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not update gender!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} catch(ResourceNotFoundException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not create gender!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
		}

		json.putPOJO("updatedGender", updatedGender);

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<ObjectNode> deleteGenderById(@PathVariable(value = "id") UUID id) {
		ObjectNode json = this.mapper.createObjectNode();
		boolean isFailure;

		try {
			isFailure = this.genderService.deleteGenderById(id);
		} catch(IllegalArgumentException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not delete gender!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
		}
		if(isFailure) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not delete gender!");
			errors.put("errorMessage", "Something went wrong with this request");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		json.put("response", "Gender with Id " + id + " has been successfully deleted");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(json);
	}
}