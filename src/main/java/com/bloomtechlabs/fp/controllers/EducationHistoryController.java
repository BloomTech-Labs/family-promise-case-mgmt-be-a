package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.EducationHistory;
import com.bloomtechlabs.fp.services.EducationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
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