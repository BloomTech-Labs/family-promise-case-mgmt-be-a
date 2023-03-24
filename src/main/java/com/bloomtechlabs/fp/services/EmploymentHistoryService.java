package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.EmploymentHistory;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.EmploymentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmploymentHistoryService {
    @Autowired
    EmploymentHistoryRepository employmentHistoryRepository;

    public List<EmploymentHistory> getAllEmploymentHistories() {
        return employmentHistoryRepository.findAll();
    }

    /**
     * @param offset page index to return results from.
     * @param limit number of results to include per page.
     * @return returns a paginated list of EmploymentHistory objects.
     */
    public Page<EmploymentHistory> getAllEmploymentHistoriesPaginated(int offset, int limit) {
        return employmentHistoryRepository.findAll(PageRequest.of(offset, limit));
    }

    public EmploymentHistory createEmploymentHistory(EmploymentHistory employmentHistory) {
        return employmentHistoryRepository.save(employmentHistory);
    }

    public ResponseEntity<EmploymentHistory> getEmploymentHistoryById(UUID id) {
        EmploymentHistory employmentHistory = findEmploymentHistoryById(id);

        return ResponseEntity.ok(employmentHistory);
    }

    public ResponseEntity<EmploymentHistory> updateEmploymentHistory(UUID id, EmploymentHistory employmentHistoryDetails) {
        EmploymentHistory employmentHistory = findEmploymentHistoryById(id);

        employmentHistory.setClientId(            employmentHistoryDetails.getClientId());
        employmentHistory.setCurrentlyEmployed(   employmentHistoryDetails.getCurrentlyEmployed());
        employmentHistory.setSkillCertifications( employmentHistoryDetails.getSkillCertifications());

        EmploymentHistory updatedEmploymentHistory = employmentHistoryRepository.save(employmentHistory);
        return ResponseEntity.ok(updatedEmploymentHistory);
    }

    public ResponseEntity<String> deleteEmploymentHistory(UUID id) {
        EmploymentHistory employmentHistory = findEmploymentHistoryById(id);

        employmentHistoryRepository.delete(employmentHistory);

        return ResponseEntity.ok("Successfully Deleted EmploymentHistory: " + id);
    }

    public long count() {
        return employmentHistoryRepository.count();
    }

    private EmploymentHistory findEmploymentHistoryById(UUID id) {
        try {
            return employmentHistoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("EmploymentHistory Does Not Exist with this Id: " + id));
        } catch(ResourceNotFoundException e) {
            return null;
        }
    }
}
