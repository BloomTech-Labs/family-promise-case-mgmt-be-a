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

    public EmploymentHistory createEmploymentHistory(EmploymentHistory employmentHistory) throws IllegalArgumentException {
        if(employmentHistory == null) {
            throw new IllegalArgumentException("EmploymentHistory input cannot be null");
        }

        return this.employmentHistoryRepository.save(employmentHistory);
    }

    public EmploymentHistory getEmploymentHistoryById(UUID id) throws ResourceNotFoundException {
        return this.findEmploymentHistoryById(id);
    }

    public EmploymentHistory updateEmploymentHistory(EmploymentHistory updatedEmployeeHistory) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedEmployeeHistory == null) {
            throw new IllegalArgumentException("EmploymentHistory input cannot be null");
        }

        EmploymentHistory currentHistory = this.findEmploymentHistoryById(updatedEmployeeHistory.getId());

        currentHistory = currentHistory.toBuilder()
                .withClientId(updatedEmployeeHistory.getClientId())
                .withCurrentlyEmployed(updatedEmployeeHistory.getCurrentlyEmployed())
                .withSkillCertifications(updatedEmployeeHistory.getSkillCertifications())
                .build();

        return this.employmentHistoryRepository.save(currentHistory);
    }

    public boolean deleteEmploymentHistoryById(UUID id) throws ResourceNotFoundException {
        if(!this.employmentHistoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("EmploymentHistory Does Not Exist with this Id: " + id);
        }

        this.employmentHistoryRepository.deleteById(id);

        return this.employmentHistoryRepository.existsById(id);
    }

    public long count() {
        return employmentHistoryRepository.count();
    }

    private EmploymentHistory findEmploymentHistoryById(UUID id) throws ResourceNotFoundException {
        return employmentHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmploymentHistory Does Not Exist with this Id: " + id));
    }
}
