package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.PhoneNumber;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.services.PhoneNumberService;
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
@RequestMapping(value = "/phone-numbers")
public class PhoneNumberController {

	@Autowired
	private PhoneNumberService phoneNumberService;

	private final ObjectMapper mapper = new ObjectMapper();

	@GetMapping(value = "")
	public List<PhoneNumber> getAllPhoneNumbers() {
		return this.phoneNumberService.getAllPhoneNumbers();
	}

	@GetMapping(value = "{offset}/{limit}")
	public ResponseEntity<Page<PhoneNumber>> getAllPhoneNumbersPaginated(
		@PathVariable(value = "offset") int offset,
		@PathVariable(value = "limit") int limit
	) {
		return ResponseEntity.ok(this.phoneNumberService.getAllPhoneNumbersPaginated(offset, limit));
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<ObjectNode> getPhoneNumberById(@PathVariable(value = "id") UUID id) {
		ObjectNode json;
		PhoneNumber phoneNumber;

		try {
			phoneNumber = this.phoneNumberService.getPhoneNumberById(id);
		} catch(ResourceNotFoundException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not get phoneNumber!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		json = this.mapper.convertValue(phoneNumber, ObjectNode.class);

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@PostMapping(value = "")
	public ResponseEntity<ObjectNode> createPhoneNumber(@RequestBody PhoneNumber phoneNumber) {
		try {
			this.phoneNumberService.createPhoneNumber(phoneNumber);
		} catch(IllegalArgumentException e) {
			ObjectNode json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not create phoneNumber!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(value = "")
	public ResponseEntity<ObjectNode> updatePhoneNumber(@RequestBody PhoneNumber phoneNumber) {
		ObjectNode json = this.mapper.createObjectNode();
		PhoneNumber updatedPhoneNumber;

		try {
			updatedPhoneNumber = this.phoneNumberService.updatePhoneNumber(phoneNumber);
		} catch(IllegalArgumentException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not update phoneNumber!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} catch(ResourceNotFoundException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not create phoneNumber!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
		}

		json.putPOJO("updatedPhoneNumber", updatedPhoneNumber);

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<ObjectNode> deletePhoneNumberById(@PathVariable(value = "id") UUID id) {
		ObjectNode json = this.mapper.createObjectNode();
		boolean isFailure;

		try {
			isFailure = this.phoneNumberService.deletePhoneNumberById(id);
		} catch(IllegalArgumentException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not delete phoneNumber!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
		}
		if(isFailure) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not delete phoneNumber!");
			errors.put("errorMessage", "Something went wrong with this request");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		json.put("response", "Phone Number with Id " + id + " has been successfully deleted");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(json);
	}
}