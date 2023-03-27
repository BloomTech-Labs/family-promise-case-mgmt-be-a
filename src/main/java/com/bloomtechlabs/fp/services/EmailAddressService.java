package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.EmailAddress;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.EmailAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EmailAddressService {
    @Autowired
    private EmailAddressRepository emailAddressRepository;

    public List<EmailAddress> getAllEmailAddresses() {
        return this.emailAddressRepository.findAll();
    }

    public Page<EmailAddress> getAllEmailAddressesPaginated(int offset, int limit) {
        return this.emailAddressRepository.findAll(PageRequest.of(offset, limit));
    }

    public EmailAddress getEmailAddressesById(UUID id) throws ResourceNotFoundException {
        EmailAddress addresses  = this.findEmailAddressById(id);

        if(addresses.getDeletedAt() != null) {
            throw new ResourceNotFoundException("Email Address does not exist with this Id: " + id);
        }

        return addresses;
    }

    public EmailAddress createEmailAddress(EmailAddress emailAddress) throws IllegalArgumentException {
        if(emailAddress == null) {
            throw new IllegalArgumentException("Email Address input cannot be null");
        }

        return this.emailAddressRepository.save(emailAddress);
    }

    public EmailAddress updateEmailAddress(EmailAddress updatedEmailAddresses) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedEmailAddresses == null) {
            throw new IllegalArgumentException("Email Address input cannot be null");
        }

        EmailAddress currentEmailAddresses = this.findEmailAddressById(updatedEmailAddresses.getId());

        currentEmailAddresses = currentEmailAddresses.toBuilder()
                .withEmail(updatedEmailAddresses.getEmail())
                .withEmailType(updatedEmailAddresses.getEmailType())
                .withAllowSMS(updatedEmailAddresses.getAllowSMS())
                .build();

        return this.emailAddressRepository.save(currentEmailAddresses);
    }

    public boolean deleteEmailAddressById(UUID id) throws IllegalArgumentException, ResourceNotFoundException {
        if(!this.emailAddressRepository.existsById(id)) {
            throw new IllegalArgumentException("Email Address does not exist with this Id: " + id);
        }

        EmailAddress address = this.findEmailAddressById(id);

        address = address.toBuilder()
                .withDeletedAt(Date.from(Instant.now()))
                .build();

        address = this.emailAddressRepository.save(address);

        return address.getDeletedAt() != null;
    }


    private EmailAddress findEmailAddressById(UUID id) throws ResourceNotFoundException {
        return this.emailAddressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Email Address does not exist with this Id: " + id));
    }

}