package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.ContactPreferences;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.services.ContactPreferencesService;
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
@RequestMapping(value = "/contact-preferences")
public class ContactPreferencesController {

	@Autowired
	private ContactPreferencesService contactPreferencesService;

	private final ObjectMapper mapper = new ObjectMapper();

	@GetMapping(value = "")
	public List<ContactPreferences> getAllContactPreferencess() {
		return this.contactPreferencesService.getAllContactPreferences();
	}

	@GetMapping(value = "{offset}/{limit}")
	public ResponseEntity<Page<ContactPreferences>> getAllContactPreferencessPaginated(
		@PathVariable(value = "offset") int offset,
		@PathVariable(value = "limit") int limit
	) {
		return ResponseEntity.ok(this.contactPreferencesService.getAllContactPreferencesPaginated(offset, limit));
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<ObjectNode> getContactPreferencesById(@PathVariable(value = "id") UUID id) {
		ObjectNode json;
		ContactPreferences contactPreferences;

		try {
			contactPreferences = this.contactPreferencesService.getContactPreferencesById(id);
		} catch(ResourceNotFoundException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not get contactPreferences!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		json = this.mapper.convertValue(contactPreferences, ObjectNode.class);

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@PostMapping(value = "")
	public ResponseEntity<ObjectNode> createContactPreferences(@RequestBody ContactPreferences contactPreferences) {
		try {
			this.contactPreferencesService.createContactPreferences(contactPreferences);
		} catch(IllegalArgumentException e) {
			ObjectNode json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not create contactPreferences!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(value = "")
	public ResponseEntity<ObjectNode> updateContactPreferences(@RequestBody ContactPreferences contactPreferences) {
		ObjectNode json = this.mapper.createObjectNode();
		ContactPreferences updatedContactPreferences;

		try {
			updatedContactPreferences = this.contactPreferencesService.updateContactPreferences(contactPreferences);
		} catch(IllegalArgumentException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not update contactPreferences!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} catch(ResourceNotFoundException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not create contactPreferences!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
		}

		json.putPOJO("updatedContactPreferences", updatedContactPreferences);

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<ObjectNode> deleteContactPreferencesById(@PathVariable(value = "id") UUID id) {
		ObjectNode json = this.mapper.createObjectNode();
		boolean isFailure;

		try {
			isFailure = this.contactPreferencesService.deleteContactPreferencesById(id);
		} catch(IllegalArgumentException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not delete contactPreferences!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
		}
		if(isFailure) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not delete contactPreferences!");
			errors.put("errorMessage", "Something went wrong with this request");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		json.put("response", "Contact Preferences with Id " + id + " has been successfully deleted");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(json);
	}
}