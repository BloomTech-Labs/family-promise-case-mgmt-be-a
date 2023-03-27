package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.PhoneNumber;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class PhoneNumberService {
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public List<PhoneNumber> getAllPhoneNumbers() {
        return this.phoneNumberRepository.findAll();
    }

    public Page<PhoneNumber> getAllPhoneNumbersPaginated(int offset, int limit) {
        return this.phoneNumberRepository.findAll(PageRequest.of(offset, limit));
    }

    public PhoneNumber getPhoneNumberById(UUID id) throws ResourceNotFoundException {
        PhoneNumber phoneNumber = this.findPhoneNumberById(id);

        if(phoneNumber.getDeletedAt() != null) {
            throw new ResourceNotFoundException("Phone Number does not exist with Id: " + id);
        }

        return phoneNumber;
    }

    public PhoneNumber createPhoneNumber(PhoneNumber phoneNumber) throws IllegalArgumentException {
        if(phoneNumber == null) {
            throw new IllegalArgumentException("Phone Number input cannot be null");
        }

        return this.phoneNumberRepository.save(phoneNumber);
    }

    public PhoneNumber updatePhoneNumber(PhoneNumber updatedPhoneNumber) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedPhoneNumber == null) {
            throw new IllegalArgumentException("Phone Number input cannot be null");
        }

        PhoneNumber currentPhoneNumber = this.findPhoneNumberById(updatedPhoneNumber.getId());

        currentPhoneNumber = currentPhoneNumber.toBuilder()
                .withNumber(updatedPhoneNumber.getNumber())
                .withPhoneType(updatedPhoneNumber.getPhoneType())
                .build();

        return this.phoneNumberRepository.save(currentPhoneNumber);
    }

    public boolean deletePhoneNumberById(UUID id) throws IllegalArgumentException, ResourceNotFoundException {
        if(!this.phoneNumberRepository.existsById(id)) {
            throw new IllegalArgumentException("Phone Number does not exist with this Id: " + id);
        }

        PhoneNumber phoneNumber = this.findPhoneNumberById(id);

        phoneNumber = phoneNumber.toBuilder()
                .withDeletedAt(Date.from(Instant.now()))
                .build();

        phoneNumber = this.phoneNumberRepository.save(phoneNumber);

        return phoneNumber.getDeletedAt() != null;
    }


    private PhoneNumber findPhoneNumberById(UUID id) throws ResourceNotFoundException {
        return this.phoneNumberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Phone Number does not exist with this Id: " + id));
    }
}