package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Ethnicity;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.EthnicityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EthnicityService {
    @Autowired
    private EthnicityRepository ethnicityRepository;

    public List<Ethnicity> getAllEthnicities() {
        return this.ethnicityRepository.findAll();
    }

    public Page<Ethnicity> getAllEthnicitiesPaginated(int offset, int limit) {
        return this.ethnicityRepository.findAll(PageRequest.of(offset, limit));
    }

    public Ethnicity getEthnicityById(UUID id) throws ResourceNotFoundException {
        return this.findEthnicityById(id);
    }

    public Ethnicity createEthnicity(Ethnicity ethnicity) throws IllegalArgumentException {
        if(ethnicity == null) {
            throw new IllegalArgumentException("Ethnicity input cannot be null");
        }

        return this.ethnicityRepository.save(ethnicity);
    }

    public Ethnicity updateEthnicity(Ethnicity updatedEthnicity) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedEthnicity == null) {
            throw new IllegalArgumentException("Ethnicity input cannot be null");
        }

        Ethnicity currentEthnicity = this.findEthnicityById(updatedEthnicity.getId());

        currentEthnicity = currentEthnicity.toBuilder()
                .withName(updatedEthnicity.getName())
                .build();

        return this.ethnicityRepository.save(currentEthnicity);
    }

    public boolean deleteEthnicityById(UUID id) throws IllegalArgumentException {
        if(!this.ethnicityRepository.existsById(id)) {
            throw new IllegalArgumentException("Ethnicity does not exist with this Id: " + id);
        }

        this.ethnicityRepository.deleteById(id);

        return this.ethnicityRepository.existsById(id);
    }


    private Ethnicity findEthnicityById(UUID id) throws ResourceNotFoundException {
        return this.ethnicityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ethnicity does not exist with this Id: " + id));
    }
}