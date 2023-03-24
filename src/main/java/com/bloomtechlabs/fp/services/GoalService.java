package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Goal;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.GoalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    /**
     * @param offset page index to return results from.
     * @param limit number of results to include per page.
     * @return returns a paginated list.
     */
    public Page<Goal> getAllGoalsPaginated(int offset, int limit) {
        return goalRepository.findAll(PageRequest.of(offset, limit));
    }

    public Goal createGoal(Goal goal) throws IllegalArgumentException {
        if (goal == null) {
            throw new IllegalArgumentException("Goal input cannot be null");
        }

        return goalRepository.save(goal);
    }

    public Goal getGoalById(UUID id) throws ResourceNotFoundException {
        return this.findGoalById(id);
    }

    public Goal updateGoal(Goal updatedGoal) throws IllegalArgumentException, ResourceNotFoundException {
        if (updatedGoal == null) {
            throw new IllegalArgumentException("Goal input cannot be null");
        }

        Goal currentGoal = this.findGoalById(updatedGoal.getId());

        currentGoal = currentGoal.toBuilder()
                .withClientId(updatedGoal.getClientId())
                .withGoalStatement(updatedGoal.getGoalStatement())
                .withGoalSteps(updatedGoal.getGoalSteps())
                .withGoalTargetDate(updatedGoal.getGoalTargetDate())
                .withCmTask(updatedGoal.getCmTask())
                .withDateArchived(updatedGoal.getDateArchived())
                .withNotes(updatedGoal.getNotes())
                .withClientStrengths(updatedGoal.getClientStrengths())
                .withClientObstacles(updatedGoal.getClientObstacles())
                .withProgressSummary(updatedGoal.getProgressSummary())
                .build();

        return this.goalRepository.save(currentGoal);
    }

    public boolean deleteGoalById(UUID id) throws IllegalArgumentException {
        if(!this.goalRepository.existsById(id)) {
            throw new IllegalArgumentException("Goal Does Not Exist with this Id: " + id);
        }

        goalRepository.deleteById(id);

        return this.goalRepository.existsById(id);
    }

    public long count() {
        return goalRepository.count();
    }

    private Goal findGoalById(UUID id) throws ResourceNotFoundException {
        return goalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goal Does Not Exist with this Id: " + id));
    }
}
