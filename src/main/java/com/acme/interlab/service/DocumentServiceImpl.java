package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Company;
import com.acme.interlab.model.Document;
import com.acme.interlab.model.Internship;
import com.acme.interlab.repository.DocumentRepository;
import com.acme.interlab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService{

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<Document> getAllDocumentsByUserId(Long userId, Pageable pageable) {
        return documentRepository.findByUserId(userId, pageable);
    }

    @Override
    public Document getDocumentByIdAndUserId(Long userId, Long documentId) {
        return documentRepository.findByIdAndUserId(documentId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Document not found with Id " + documentId +
                                " and UserId " + userId
                ));
    }

    @Override
    public Document createDocument(Long userId, Document document) {
        return userRepository.findById(userId).map(user ->{
            document.setUser(user);
            return documentRepository.save(document);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "User", "Id", userId

        ));
    }

    @Override
    public Document updateDocument(Long userId, Long documentId, Document documentDetails) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);

        return documentRepository.findById(documentId).map(document -> {
            document.setName(documentDetails.getName());
            document.setDescription(documentDetails.getDescription());
            return documentRepository.save(document);
        }).orElseThrow(() -> new ResourceNotFoundException("Document", "Id", documentId));
    }

    @Override
    public ResponseEntity<?> deleteDocument(Long userId, Long documentId) {
        return documentRepository.findByIdAndUserId(documentId, userId).map(document ->{
            documentRepository.delete(document);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                " Document not found with Id " + documentId + " and UserId " + userId
        ));
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
}
