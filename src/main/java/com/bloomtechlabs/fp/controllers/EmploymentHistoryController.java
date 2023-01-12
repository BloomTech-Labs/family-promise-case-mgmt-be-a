package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.EmploymentHistory;
import com.bloomtechlabs.fp.services.EmploymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employment_histories")
public class EmploymentHistoryController {

    @Autowired
    EmploymentHistoryService employmentHistoryService;

    @GetMapping
    List<EmploymentHistory> getAllEmploymentHistories() {
        return employmentHistoryService.getAllEmploymentHistories();
    }

    @PostMapping
    EmploymentHistory createEmploymentHistory(@RequestBody EmploymentHistory employmentHistory) {
        return employmentHistoryService.createEmploymentHistory(employmentHistory);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmploymentHistory> getEmploymentHistoryById(@PathVariable Long id) {
        return employmentHistoryService.getEmploymentHistoryById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmploymentHistory> updateEmploymentHistory(@PathVariable Long id,
                                                                     @RequestBody EmploymentHistory employmentHistory) {
        return employmentHistoryService.updateEmploymentHistory(id, employmentHistory);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmploymentHistory(@PathVariable Long id) {
        return employmentHistoryService.deleteEmploymentHistory(id);
    }
}
