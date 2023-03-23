package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.EducationHistory;
import com.bloomtechlabs.fp.services.EducationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/education_histories")
public class EducationHistoryController {

    @Autowired
    EducationHistoryService educationHistoryService;

    @GetMapping
    List<EducationHistory> getAllEducationHistories() {
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

    @PostMapping
    EducationHistory createEducationHistory(@RequestBody EducationHistory educationHistory) {
        return educationHistoryService.createEducationHistory(educationHistory);
    }

    @GetMapping("{id}")
    public ResponseEntity<EducationHistory> getEducationHistory(@PathVariable UUID id) {
        return educationHistoryService.getEducationHistoryById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<EducationHistory> updateEducationHistory(@PathVariable UUID id,
                                                                   @RequestBody EducationHistory educationHistory) {
        return educationHistoryService.updateEducationHistory(id,educationHistory);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEducationHistory(@PathVariable UUID id) {
        return educationHistoryService.deleteEducationHistory(id);
    }
}