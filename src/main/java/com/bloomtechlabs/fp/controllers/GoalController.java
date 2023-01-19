package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.Goal;
import com.bloomtechlabs.fp.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    GoalService goalService;

    @GetMapping
    public List<Goal> findAllGoals() {
        return goalService.getAllGoals();
    }

    @GetMapping("{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable UUID id) {
        return goalService.getGoalById(id);
    }

    @PostMapping
    public Goal createGoal(@RequestBody Goal goal) {
        return goalService.createGoal(goal);
    }

    @PutMapping("{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable UUID id, @RequestBody Goal goal) {
        return goalService.updateGoal(id, goal);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGoal(@PathVariable UUID id) {
        return goalService.deleteGoal(id);
    }

}