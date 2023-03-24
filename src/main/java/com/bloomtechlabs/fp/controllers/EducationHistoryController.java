package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.EducationHistory;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.services.EducationHistoryService;
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
@RequestMapping("/education_histories")
public class EducationHistoryController {

    @Autowired
    private EducationHistoryService educationHistoryService;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public List<EducationHistory> getAllEducationHistories() {
        return educationHistoryService.getAllEducationHistories();
    }

    /**
     * @param offset page index to return results from.
     * @param limit number of results to include per page.
     * @return returns a paginated list.
     */
    @GetMapping("{offset}/{limit}")
    public ResponseEntity<Page<EducationHistory>> getAllEducationHistoriesPaginated(@PathVariable int offset, @PathVariable int limit) {
        return ResponseEntity.ok(educationHistoryService.getAllEducationHistoriesPaginated(offset, limit));
    }

    @GetMapping("{id}")
    public ResponseEntity<ObjectNode> getEducationHistory(@PathVariable(value = "id") UUID id) {
        ObjectNode json;
        EducationHistory history;

        try {
            history = this.educationHistoryService.getEducationHistoryById(id);
        } catch(IllegalArgumentException e) {
            json = this.mapper.createObjectNode();
            ObjectNode errors = json.putObject("error");

            errors.put("userResponse", "Could not retrieve EducationHistory!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        }

        json = this.mapper.convertValue(history, ObjectNode.class);

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @PostMapping
    public ResponseEntity<ObjectNode> createEducationHistory(@RequestBody EducationHistory educationHistory) {
        try {
            this.educationHistoryService.createEducationHistory(educationHistory);
        } catch(IllegalArgumentException e) {
            ObjectNode json = this.mapper.createObjectNode();
            ObjectNode errors = json.putObject("error");
            errors.put("userResponse", "Could not create EducationHistory!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<ObjectNode> updateEducationHistory(@RequestBody EducationHistory educationHistory) {
        ObjectNode json = this.mapper.createObjectNode();
        EducationHistory history;

        try {
            history = this.educationHistoryService.updateEducationHistory(educationHistory);
        } catch(IllegalArgumentException e) {
            ObjectNode errors = json.putObject("errors");
            errors.put("userResponse", "Could not update EducationHistory!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        } catch(ResourceNotFoundException e) {
            ObjectNode errors = json.putObject("errors");
            errors.put("userResponse", "Could not update EducationHistory!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        }

        json.putPOJO("updatedEducationHistory", history);

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ObjectNode> deleteEducationHistory(@PathVariable UUID id) {
        ObjectNode json = this.mapper.createObjectNode();
        boolean isFailure;

        try {
            isFailure = this.educationHistoryService.deleteEducationHistoryById(id);
        } catch(IllegalArgumentException e) {
            ObjectNode errors = json.putObject("error");
            errors.put("userResponse", "Could not delete EducationHistory!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        }
        if(isFailure) {
            ObjectNode errors = json.putObject("error");
            errors.put("userResponse", "Could not delete EducationHistory!");
            errors.put("errorMessage", "Something went wrong with this request");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

        json.put("response", "EducationHistory with Id " + id + " has been successfully deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(json);
    }
}