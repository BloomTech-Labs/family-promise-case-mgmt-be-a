package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Disability;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.DisabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DisabilityService {
    @Autowired
    private DisabilityRepository disabilityRepository;

    public List<Disability> getAllDisabilities() {
        return this.disabilityRepository.findAll();
    }

    public Page<Disability> getAllDisabilitiesPaginated(int offset, int limit) {
        return this.disabilityRepository.findAll(PageRequest.of(offset, limit));
    }

    public Disability getDisabilityById(UUID id) throws ResourceNotFoundException {
        return this.findDisabilityById(id);
    }

    public Disability createDisability(Disability disability) throws IllegalArgumentException {
        if(disability == null) {
            throw new IllegalArgumentException("Disability input cannot be null");
        }

        return this.disabilityRepository.save(disability);
    }

    public Disability updateDisability(Disability updatedDisability) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedDisability == null) {
            throw new IllegalArgumentException("Disability input cannot be null");
        }

        Disability currentDisability = this.findDisabilityById(updatedDisability.getId());

        currentDisability = currentDisability.toBuilder()
                .withDisability(updatedDisability.getDisability())
                .build();


        return this.disabilityRepository.save(currentDisability);
    }

    public boolean deleteDisabilityById(UUID id) throws IllegalArgumentException {
        if(!this.disabilityRepository.existsById(id)) {
            throw new IllegalArgumentException("Disability does not exist with this Id: " + id);
        }

        this.disabilityRepository.deleteById(id);

        return this.disabilityRepository.existsById(id);
    }


    private Disability findDisabilityById(UUID id) throws ResourceNotFoundException {
        return this.disabilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disability does not exist with this Id: " + id));
    }
}