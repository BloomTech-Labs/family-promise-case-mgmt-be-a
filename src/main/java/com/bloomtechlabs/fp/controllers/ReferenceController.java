package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.Reference;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.services.ReferenceService;
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
@RequestMapping(value = "/references")
public class ReferenceController {

	@Autowired
	private ReferenceService referenceService;

	private final ObjectMapper mapper = new ObjectMapper();

	@GetMapping(value = "")
	public List<Reference> getAllReferences() {
		return this.referenceService.getAllReferences();
	}

	@GetMapping(value = "{offset}/{limit}")
	public ResponseEntity<Page<Reference>> getAllReferencesPaginated(
		@PathVariable(value = "offset") int offset,
		@PathVariable(value = "limit") int limit
	) {
		return ResponseEntity.ok(this.referenceService.getAllReferencesPaginated(offset, limit));
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<ObjectNode> getReferenceById(@PathVariable(value = "id") UUID id) {
		ObjectNode json;
		Reference reference;

		try {
			reference = this.referenceService.getReferenceById(id);
		} catch(ResourceNotFoundException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not get reference!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		json = this.mapper.convertValue(reference, ObjectNode.class);

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@PostMapping(value = "")
	public ResponseEntity<ObjectNode> createReference(@RequestBody Reference reference) {
		try {
			this.referenceService.createReference(reference);
		} catch(IllegalArgumentException e) {
			ObjectNode json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not create reference!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(value = "")
	public ResponseEntity<ObjectNode> updateReference(@RequestBody Reference reference) {
		ObjectNode json = this.mapper.createObjectNode();
		Reference updatedReference;

		try {
			updatedReference = this.referenceService.updateReference(reference);
		} catch(IllegalArgumentException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not update reference!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} catch(ResourceNotFoundException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not create reference!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
		}

		json.putPOJO("updatedReference", updatedReference);

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<ObjectNode> deleteReferenceById(@PathVariable(value = "id") UUID id) {
		ObjectNode json = this.mapper.createObjectNode();
		boolean isFailure;

		try {
			isFailure = this.referenceService.deleteReferenceById(id);
		} catch(IllegalArgumentException e) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not delete reference!");
			errors.put("errorMessage", e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
		}
		if(isFailure) {
			json = this.mapper.createObjectNode();
			ObjectNode errors = json.putObject("error");

			errors.put("userResponse", "Could not delete reference!");
			errors.put("errorMessage", "Something went wrong with this request");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		json.put("response", "Reference with Id " + id + " has been successfully deleted");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(json);
	}
}