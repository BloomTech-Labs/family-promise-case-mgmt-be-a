package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.EmploymentHistory;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.services.EmploymentHistoryService;
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
@RequestMapping("/employment_histories")
public class EmploymentHistoryController {

    @Autowired
    private EmploymentHistoryService employmentHistoryService;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public List<EmploymentHistory> getAllEmploymentHistories() {
        return employmentHistoryService.getAllEmploymentHistories();
    }

    /**
     * @param offset page index to return results from.
     * @param limit number of results to include per page.
     * @return returns a paginated list of EmploymentHistory objects.
     */
    @GetMapping("{offset}/{limit}")
    public ResponseEntity<Page<EmploymentHistory>> getAllEmploymentHistoriesPaginated(@PathVariable int offset, @PathVariable int limit) {
        return ResponseEntity.ok(employmentHistoryService.getAllEmploymentHistoriesPaginated(offset, limit));
    }

    @GetMapping("{id}")
    public ResponseEntity<ObjectNode> getEmploymentHistoryById(@PathVariable UUID id) {
        ObjectNode json;
        EmploymentHistory history;

        try {
            history = this.employmentHistoryService.getEmploymentHistoryById(id);
        } catch(ResourceNotFoundException e) {
            json = this.mapper.createObjectNode();
            ObjectNode errors = json.putObject("error");

            errors.put("userResponse", "Could not retrieve EmploymentHistory!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        }

        json = this.mapper.convertValue(history, ObjectNode.class);

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @PostMapping
    public ResponseEntity<ObjectNode> createEmploymentHistory(@RequestBody EmploymentHistory employmentHistory) {
        try {
            this.employmentHistoryService.createEmploymentHistory(employmentHistory);
        } catch(IllegalArgumentException e) {
            ObjectNode json = this.mapper.createObjectNode();
            ObjectNode errors = json.putObject("error");
            errors.put("userResponse", "Could not create EmploymentHistory!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<ObjectNode> updateEmploymentHistory(@RequestBody EmploymentHistory employmentHistory) {
        ObjectNode json = this.mapper.createObjectNode();
        EmploymentHistory history;

        try {
            history = this.employmentHistoryService.updateEmploymentHistory(employmentHistory);
        } catch(IllegalArgumentException e) {
            ObjectNode errors = json.putObject("errors");
            errors.put("userResponse", "Could not update EmploymentHistory!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        } catch(ResourceNotFoundException e) {
            ObjectNode errors = json.putObject("errors");
            errors.put("userResponse", "Could not update EmploymentHistory!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        }

        json.putPOJO("updatedEmploymentHistory", history);

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ObjectNode> deleteEmploymentHistory(@PathVariable UUID id) {
        ObjectNode json = this.mapper.createObjectNode();
        boolean isFailure;

        try {
            isFailure = this.employmentHistoryService.deleteEmploymentHistoryById(id);
        } catch(ResourceNotFoundException e) {
            ObjectNode errors = json.putObject("error");
            errors.put("userResponse", "Could not delete EmployeeHistory!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        }
        if(isFailure) {
            ObjectNode errors = json.putObject("error");
            errors.put("userResponse", "Could not delete EmployeeHistory!");
            errors.put("errorMessage", "Something went wrong with this request");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

        json.put("response", "EmploymentHistory with Id " + id + " has been successfully deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(json);
    }
}
