package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Profile;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getAllProfiles() {
        return this.profileRepository.findAll();
    }

    public Page<Profile> getAllProfilesPaginated(int offset, int limit) {
        return this.profileRepository.findAll(PageRequest.of(offset, limit));
    }

    public Profile getProfileById(String id) throws ResourceNotFoundException {
        return this.findProfileById(id);
    }

    public Profile createProfile(Profile profile) throws IllegalArgumentException {
        if(profile == null) {
            throw new IllegalArgumentException("Profile input cannot be null");
        }

        return this.profileRepository.save(profile);
    }

    public Profile updateProfile(Profile updatedProfile) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedProfile == null) {
            throw new IllegalArgumentException("Profile input cannot be null");
        }

        Profile currentProfile = this.findProfileById(updatedProfile.getId());

        return this.profileRepository.save(currentProfile);
    }

    public boolean deleteProfileById(String id) throws IllegalArgumentException {
       if(!this.profileRepository.existsById(id)) {
           throw new IllegalArgumentException("Profile does not exist with this Id: " + id);
       }

       this.profileRepository.deleteById(id);

       return this.profileRepository.existsById(id);
    }


    private Profile findProfileById(String id) throws ResourceNotFoundException {
        return this.profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile does not exist with this Id: " + id));
    }
}