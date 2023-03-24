package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.EducationHistory;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.EducationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EducationHistoryService {
    @Autowired
    EducationHistoryRepository educationHistoryRepository;

    public List<EducationHistory> getAllEducationHistories() {
        return educationHistoryRepository.findAll();
    }

    /**
     * @param offset page index to return results from.
     * @param limit number of results to include per page.
     * @return returns a paginated list.
     */
    public Page<EducationHistory> getAllEducationHistoriesPaginated(int offset, int limit) {
        return educationHistoryRepository.findAll(PageRequest.of(offset, limit));
    }

    public EducationHistory createEducationHistory(EducationHistory educationHistory) throws IllegalArgumentException {
        if(educationHistory == null) {
            throw new IllegalArgumentException("EducationHistory input cannot be null");
        }

        return this.educationHistoryRepository.save(educationHistory);
    }

    public EducationHistory getEducationHistoryById(UUID id) throws ResourceNotFoundException {
        return this.findEducationHistoryById(id);
    }

    public EducationHistory updateEducationHistory(EducationHistory updatedEducationHistory) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedEducationHistory == null) {
            throw new IllegalArgumentException("EducationHistory input cannot be null");
        }

        EducationHistory currentEducationHistory = this.findEducationHistoryById(updatedEducationHistory.getId());

        currentEducationHistory = currentEducationHistory.toBuilder()
                .withClientId(updatedEducationHistory.getClientId())
                .withSchoolName(updatedEducationHistory.getSchoolName())
                .withLevel(updatedEducationHistory.getLevel())
                .withStartDate(updatedEducationHistory.getStartDate())
                .withEndDate(updatedEducationHistory.getEndDate())
                .build();

        return this.educationHistoryRepository.save(currentEducationHistory);
    }

    public boolean deleteEducationHistoryById(UUID id) throws IllegalArgumentException {
        if(!this.educationHistoryRepository.existsById(id)) {
            throw new IllegalArgumentException("EducationHistory does not exist with this Id: " + id);
        }

        this.educationHistoryRepository.deleteById(id);

        return this.educationHistoryRepository.existsById(id);
    }

    public long count() {
        return educationHistoryRepository.count();
    }

    private EducationHistory findEducationHistoryById(UUID id) throws ResourceNotFoundException {
        return educationHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EducationHistory Does Not Exist with this Id: " + id));
    }
}
