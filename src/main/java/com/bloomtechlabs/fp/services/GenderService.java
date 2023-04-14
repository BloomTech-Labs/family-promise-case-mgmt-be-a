package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Gender;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GenderService {
    @Autowired
    private GenderRepository genderRepository;

    public List<Gender> getAllGenders() {
        return this.genderRepository.findAll();
    }

    public Page<Gender> getAllGendersPaginated(int offset, int limit) {
        return this.genderRepository.findAll(PageRequest.of(offset, limit));
    }

    public Gender getGenderById(UUID id) throws ResourceNotFoundException {
        return this.findGenderById(id);
    }

    public Gender createGender(Gender gender) throws IllegalArgumentException {
        if(gender == null) {
            throw new IllegalArgumentException("Gender input cannot be null");
        }

        return this.genderRepository.save(gender);
    }

    public Gender updateGender(Gender updatedGender) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedGender == null) {
            throw new IllegalArgumentException("Gender input cannot be null");
        }

        Gender currentGender = this.findGenderById(updatedGender.getId());

        currentGender = currentGender.toBuilder()
                .withName(updatedGender.getName())
                .build();

        return this.genderRepository.save(currentGender);
    }

    public boolean deleteGenderById(UUID id) throws IllegalArgumentException {
        if(!this.genderRepository.existsById(id)) {
            throw new IllegalArgumentException("Gender does not exist with this Id: " + id);
        }

        this.genderRepository.deleteById(id);

        return this.genderRepository.existsById(id);
    }


    private Gender findGenderById(UUID id) throws ResourceNotFoundException {
        return this.genderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gender does not exist with this Id: " + id));
    }
}