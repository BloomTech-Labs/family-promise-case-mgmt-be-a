package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Reference;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReferenceService {
    @Autowired
    private ReferenceRepository referenceRepository;

    public List<Reference> getAllReferences() {
        return this.referenceRepository.findAll();
    }

    public Page<Reference> getAllReferencesPaginated(int offset, int limit) {
        return this.referenceRepository.findAll(PageRequest.of(offset, limit));
    }

    public Reference getReferenceById(UUID id) throws ResourceNotFoundException {
        return this.findReferenceById(id);
    }

    public Reference createReference(Reference reference) throws IllegalArgumentException {
        if(reference == null) {
            throw new IllegalArgumentException("Reference input cannot be null");
        }

        return this.referenceRepository.save(reference);
    }

    public Reference updateReference(Reference updatedReference) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedReference == null) {
            throw new IllegalArgumentException("Reference input cannot be null");
        }

        Reference currentReference = this.findReferenceById(updatedReference.getId());

        currentReference = currentReference.toBuilder()
                .withName(updatedReference.getName())
                .withAddress(updatedReference.getAddress())
                .withCell(updatedReference.getCell())
                .withHome(updatedReference.getHome())
                .withWork(updatedReference.getWork())
                .withEmail(updatedReference.getEmail())
                .withFirstMeetingDate(updatedReference.getFirstMeetingDate())
                .build();

        return this.referenceRepository.save(currentReference);
    }

    public boolean deleteReferenceById(UUID id) throws IllegalArgumentException {
        if(!this.referenceRepository.existsById(id)) {
            throw new IllegalArgumentException("Reference does not exist with this Id: " + id);
        }

        this.referenceRepository.deleteById(id);

        return this.referenceRepository.existsById(id);
    }


    private Reference findReferenceById(UUID id) throws ResourceNotFoundException {
        return this.referenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reference does not exist with this Id: " + id));
    }
}