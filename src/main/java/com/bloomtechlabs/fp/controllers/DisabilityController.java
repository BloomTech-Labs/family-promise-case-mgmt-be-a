package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.Disability;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.services.DisabilityService;
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
@RequestMapping(value = "/disabilities")
public class DisabilityController {

	@Autowired
	private DisabilityService disabilityService;

	private final ObjectMapper mapper = new ObjectMapper();

	@GetMapping(value = "")
	public List<Disability> getAllDisabilitys() {
		return this.disabilityService.getAllDisabilities();
	}

	@GetMapping(value = "{offset}/{limit}")
	public ResponseEntity<Page<Disability>> getAllDisabilitysPaginated(
		@PathVariable(value = "offset") int offset,
		@PathVariable(value = "limit") int limit
	) {
		return ResponseEntity.ok(this.disabilityService.getAllDisabilitiesPaginated(offset, limit));
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<ObjectNode> getDisabilityById(@PathVariable(value = "id") UUID id) {
		ObjectNode json;
		Disability disability;

		try {
			disability = this.disabilityService.getDisabilityById(id);
		} catch(ResourceNotFoundException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not get disability!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		json = this.mapper.convertValue(disability, ObjectNode.class);

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@PostMapping(value = "")
	public ResponseEntity<ObjectNode> createDisability(@RequestBody Disability disability) {
		try {
			this.disabilityService.createDisability(disability);
		} catch(IllegalArgumentException e) {
			ObjectNode json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not create disability!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(value = "")
	public ResponseEntity<ObjectNode> updateDisability(@RequestBody Disability disability) {
		ObjectNode json = this.mapper.createObjectNode();
		Disability updatedDisability;

		try {
			updatedDisability = this.disabilityService.updateDisability(disability);
		} catch(IllegalArgumentException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not update disability!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} catch(ResourceNotFoundException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not create disability!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
		}

		json.putPOJO("updatedDisability", updatedDisability);

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<ObjectNode> deleteDisabilityById(@PathVariable(value = "id") UUID id) {
		ObjectNode json = this.mapper.createObjectNode();
		boolean isFailure;

		try {
			isFailure = this.disabilityService.deleteDisabilityById(id);
		} catch(IllegalArgumentException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not delete disability!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
		}
		if(isFailure) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not delete disability!");
			errors.put("errorMessage", "Something went wrong with this request");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		json.put("response", "Household with Id " + id + " has been successfully deleted");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(json);
	}
}