package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.EducationHistory;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.EducationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationHistoryService {
    @Autowired
    EducationHistoryRepository educationHistoryRepository;

    public List<EducationHistory> getAllEducationHistories() {
        return educationHistoryRepository.findAll();
    }

    public EducationHistory createEducationHistory(EducationHistory educationHistory) {
        return educationHistoryRepository.save(educationHistory);
    }

    public ResponseEntity<EducationHistory> getEducationHistoryById(Long id) {
        EducationHistory educationHistory = findEducationHistoryById(id);

        return ResponseEntity.ok(educationHistory);
    }

    public ResponseEntity<EducationHistory> updateEducationHistory(Long id, EducationHistory educationHistoryDetails) {
        EducationHistory educationHistory = findEducationHistoryById(id);

        educationHistory.setClientId(   educationHistoryDetails.getClientId());
        educationHistory.setSchoolName( educationHistoryDetails.getSchoolName());
        educationHistory.setLevel(      educationHistoryDetails.getLevel());
        educationHistory.setStartDate(  educationHistoryDetails.getStartDate());
        educationHistory.setEndDate(    educationHistoryDetails.getEndDate());

        EducationHistory updatedEducationHistory = educationHistoryRepository.save(educationHistory);
        return ResponseEntity.ok(updatedEducationHistory);
    }

    public ResponseEntity<String> deleteEducationHistory(Long id) {
        EducationHistory educationHistory = findEducationHistoryById(id);

        educationHistoryRepository.delete(educationHistory);

        return ResponseEntity.ok("Successfully Deleted EducationHistory: " + id);
    }

    public long count() {
        return educationHistoryRepository.count();
    }

    private EducationHistory findEducationHistoryById(Long id) {
        return educationHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EducationHistory Does Not Exist with this Id: " + id));

    }
}
