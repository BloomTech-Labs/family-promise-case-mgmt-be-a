package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.ClientNotes;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.ClientNotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ClientNotesService {
    @Autowired
    private ClientNotesRepository clientNotesRepository;

    public List<ClientNotes> getAllClientNotes() {
        return this.clientNotesRepository.findAll();
    }

    public Page<ClientNotes> getAllClientNotesPaginated(int offset, int limit) {
        return this.clientNotesRepository.findAll(PageRequest.of(offset, limit));
    }

    public ClientNotes getClientNotesById(UUID id) throws ResourceNotFoundException {
        ClientNotes notes =  this.findClientNotesById(id);

        if(notes.getDeletedAt() != null) {
            throw new ResourceNotFoundException("ClientNotes does not exist with this Id: " + id);
        }

        return notes;
    }

    public ClientNotes createClientNotes(ClientNotes clientNotes) throws IllegalArgumentException {
        if(clientNotes == null) {
            throw new IllegalArgumentException("ClientNotes input cannot be null");
        }

        return this.clientNotesRepository.save(clientNotes);
    }

    public ClientNotes updateClientNotes(ClientNotes updatedClientNotes) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedClientNotes == null) {
            throw new IllegalArgumentException("ClientNotes input cannot be null");
        }

        ClientNotes currentClientNotes = this.findClientNotesById(updatedClientNotes.getId());

        currentClientNotes = currentClientNotes.toBuilder()
                .withSourceView(updatedClientNotes.getSourceView())
                .withMessage(updatedClientNotes.getMessage())
                .withMessage(updatedClientNotes.getMessage())
                .build();

        return this.clientNotesRepository.save(currentClientNotes);
    }

    public boolean deleteClientNotesById(UUID id) throws IllegalArgumentException, ResourceNotFoundException {
        if(!this.clientNotesRepository.existsById(id)) {
            throw new IllegalArgumentException("ClientNotes does not exist with this Id: " + id);
        }

        ClientNotes notes = this.findClientNotesById(id);

        notes = notes.toBuilder()
                .withDeletedAt(Date.from(Instant.now()))
                .build();

        notes = this.clientNotesRepository.save(notes);

        return notes.getDeletedAt() != null;
    }


    private ClientNotes findClientNotesById(UUID id) throws ResourceNotFoundException {
        return this.clientNotesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClientNotes does not exist with this Id: " + id));
    }
}