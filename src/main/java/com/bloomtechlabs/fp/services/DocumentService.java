package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Document;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return this.documentRepository.findAll();
    }

    public Page<Document> getAllDocumentsPaginated(int offset, int limit) {
        return this.documentRepository.findAll(PageRequest.of(offset, limit));
    }

    public Document getDocumentById(UUID id) throws ResourceNotFoundException {
        return this.findDocumentById(id);
    }

    public Document createDocument(Document documents) throws IllegalArgumentException {
        if(documents == null) {
            throw new IllegalArgumentException("Document input cannot be null");
        }

        return this.documentRepository.save(documents);
    }

    public Document updateDocument(Document updatedDocument) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedDocument == null) {
            throw new IllegalArgumentException("Document input cannot be null");
        }

        Document currentDocuments = this.findDocumentById(updatedDocument.getId());

        currentDocuments = currentDocuments.toBuilder()
                .withCompletedHFCA(updatedDocument.getCompletedHFCA())
                .withValidDriver(updatedDocument.getValidDriver())
                .withValidSocial(updatedDocument.getValidSocial())
                .withDshsWicTanfSnap(updatedDocument.getDshsWicTanfSnap())
                .withResponsibleRentersCourse(updatedDocument.getResponsibleRentersCourse())
                .withBirthCertForChildren(updatedDocument.getBirthCertForChildren())
                .withChildEnrolledSchool(updatedDocument.getChildEnrolledSchool())
                .withChildcare(updatedDocument.getChildcare())
                .build();


        return this.documentRepository.save(currentDocuments);
    }

    public boolean deleteDocumentsById(UUID id) throws IllegalArgumentException {
        if(!this.documentRepository.existsById(id)) {
            throw new IllegalArgumentException("Document does not exist with this Id: " + id);
        }

        this.documentRepository.deleteById(id);

        return this.documentRepository.existsById(id);
    }


    private Document findDocumentById(UUID id) throws ResourceNotFoundException {
        return this.documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document does not exist with this Id: " + id));
    }
}