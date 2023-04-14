package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.ContactPreferences;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.ContactPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactPreferencesService {
    @Autowired
    private ContactPreferencesRepository contactPreferencesRepository;

    public List<ContactPreferences> getAllContactPreferences() {
        return this.contactPreferencesRepository.findAll();
    }

    public Page<ContactPreferences> getAllContactPreferencesPaginated(int offset, int limit) {
        return this.contactPreferencesRepository.findAll(PageRequest.of(offset, limit));
    }

    public ContactPreferences getContactPreferencesById(UUID id) throws ResourceNotFoundException {
        return this.findContactPreferencesById(id);
    }

    public ContactPreferences createContactPreferences(ContactPreferences contactPreferences) throws IllegalArgumentException {
        if(contactPreferences == null) {
            throw new IllegalArgumentException("Contact Preferences input cannot be null");
        }

        return this.contactPreferencesRepository.save(contactPreferences);
    }

    public ContactPreferences updateContactPreferences(ContactPreferences updatedContactPreferences) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedContactPreferences == null) {
            throw new IllegalArgumentException("Contact Preferences input cannot be null");
        }

        ContactPreferences currentContactPreferences = this.findContactPreferencesById(updatedContactPreferences.getId());

        currentContactPreferences = currentContactPreferences.toBuilder()
                .withFoodAssistance(updatedContactPreferences.getFoodAssistance())
                .withClothingAssistance(updatedContactPreferences.getClothingAssistance())
                .withCouncelingServices(updatedContactPreferences.getCouncelingServices())
                .withAddictionResources(updatedContactPreferences.getAddictionResources())
                .withMentorPrograms(updatedContactPreferences.getMentorPrograms())
                .withYouthServices(updatedContactPreferences.getYouthServices())
                .withBudgeting(updatedContactPreferences.getBudgeting())
                .withCanTextApartmentListings(updatedContactPreferences.getCanTextApartmentListings())
                .withCanTextCareerFairs(updatedContactPreferences.getCanTextCareerFairs())
                .withCanReferToPartners(updatedContactPreferences.getCanReferToPartners())
                .build();

        return this.contactPreferencesRepository.save(currentContactPreferences);
    }

    public boolean deleteContactPreferencesById(UUID id) throws IllegalArgumentException {
        if(!this.contactPreferencesRepository.existsById(id)) {
            throw new IllegalArgumentException("Contact Preferences does not exist with this Id: " + id);
        }

        this.contactPreferencesRepository.deleteById(id);

        return this.contactPreferencesRepository.existsById(id);
    }


    private ContactPreferences findContactPreferencesById(UUID id) throws ResourceNotFoundException {
        return this.contactPreferencesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Preferences does not exist with this Id: " + id));
    }
}