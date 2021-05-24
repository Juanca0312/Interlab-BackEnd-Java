package com.acme.interlab.service;

import com.acme.interlab.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DocumentService {
    Page<Document> getAllDocumentsByUserId(Long userId, Pageable pageable);
    Document getDocumentByIdAndUserId(Long userId, Long documentId);
    Document createDocument(Long userId,Document document);
    Document updateDocument(Long userId,Long documentId, Document documentDetails);
    ResponseEntity<?> deleteDocument(Long userId, Long documentId);

    List<Document> getAllDocuments();
}
