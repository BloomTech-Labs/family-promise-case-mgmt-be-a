package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.Goal;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.services.GoalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public List<Goal> findAllGoals() {
        return this.goalService.getAllGoals();
    }

    /**
     * @param offset page index to return results from.
     * @param limit number of results to include per page.
     * @return returns a paginated list.
     */
    @GetMapping("{offset}/{limit}")
    public ResponseEntity<Page<Goal>> findAllGoalsPaginated(@PathVariable int offset, @PathVariable int limit) {
        return ResponseEntity.ok(goalService.getAllGoalsPaginated(offset, limit));
    }

    @GetMapping("{id}")
    public ResponseEntity<ObjectNode> getGoalById(@PathVariable UUID id) {
        ObjectNode json;
        Goal goal;

        try {
            goal = this.goalService.getGoalById(id);
        } catch(ResourceNotFoundException e) {
            json = this.mapper.createObjectNode();
            ObjectNode errors = json.putObject("error");

            errors.put("userResponse", "Could not update household!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

        json = this.mapper.convertValue(goal, ObjectNode.class);

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @PostMapping
    public ResponseEntity<ObjectNode> createGoal(@RequestBody Goal goal) {
        try {
            this.goalService.createGoal(goal);
        } catch(IllegalArgumentException e) {
            ObjectNode json = this.mapper.createObjectNode();
            ObjectNode errors = json.putObject("error");
            errors.put("userResponse", "Could not create goal!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<ObjectNode> updateGoal(@RequestBody Goal goal) {
        ObjectNode json = this.mapper.createObjectNode();
        Goal updatedGoal;

        try {
            updatedGoal = this.goalService.updateGoal(goal);
        } catch(IllegalArgumentException e) {
            ObjectNode errors = json.putObject("errors");
            errors.put("userResponse", "Could not update Household!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        } catch(ResourceNotFoundException e) {
            ObjectNode errors = json.putObject("errors");
            errors.put("userResponse", "Could not update Household!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        }

        json.putPOJO("updatedGoal", updatedGoal);

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ObjectNode> deleteGoal(@PathVariable UUID id) {
        ObjectNode json = this.mapper.createObjectNode();
        boolean isFailure;

        try {
            isFailure = this.goalService.deleteGoalById(id);
        } catch(IllegalArgumentException e) {
            ObjectNode errors = json.putObject("error");
            errors.put("userResponse", "Could not delete Goal!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        }
        if(isFailure) {
            ObjectNode errors = json.putObject("error");
            errors.put("userResponse", "Could not delete Goal!");
            errors.put("errorMessage", "Something went wrong with this request");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

        json.put("response", "Goal with Id " + id + " has been successfully deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(json);
    }

}
