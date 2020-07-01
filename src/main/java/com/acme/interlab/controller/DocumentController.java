package com.acme.interlab.controller;


import com.acme.interlab.model.Document;
import com.acme.interlab.resource.DocumentResource;
import com.acme.interlab.resource.SaveDocumentResource;
import com.acme.interlab.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DocumentController {
    
@Autowired
    private ModelMapper mapper;

@Autowired
    private DocumentService documentService;

//Example of how to work with Lists instead of Pages, I strongly believe that the first one is easier to use and test.
@GetMapping("/documents")
    public List<Document> getAllDocuments() {
    return documentService.getAllDocuments();
    }

@GetMapping("/users/{userId}/documents")
    public Page<DocumentResource> getAllDocumentsByUserId(
            @PathVariable(name = "userId") Long userId,
            Pageable pageable)
{
Page<Document> documentPage = documentService.getAllDocumentsByUserId(userId, pageable);
List<DocumentResource> resources = documentPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
return new PageImpl<>(resources, pageable, resources.size());
}

@GetMapping("/users/{userId}/documents/{documentId}")
public DocumentResource getDocumentByIdAndUserId(@PathVariable(name = "userId") Long userId,
                                                 @PathVariable(name = "documentId") Long documentId){
    return convertToResource(documentService.getDocumentByIdAndUserId(userId, documentId));
}

@PostMapping("/users/{userId}/documents")
public DocumentResource createDocument(@PathVariable(name = "userId") Long userId,
                                       @Valid @RequestBody SaveDocumentResource resource){
    return convertToResource(documentService.createDocument(userId, convertToEntity(resource)));
}

@PutMapping("/users/{userId}/documents/{documentId}")
public DocumentResource updateDocument(@PathVariable(name = "userId") Long userId,
                                       @PathVariable(name = "documentId") Long documentId,
                                       @Valid @RequestBody SaveDocumentResource resource){
    return convertToResource(documentService.updateDocument(userId, documentId, convertToEntity(resource)));
}

@DeleteMapping("/users/{userId}/documents/{documentId}")
public ResponseEntity<?> deleteDocument(@PathVariable(name = "userId") Long userId,
                                        @PathVariable(name="documentId") Long documentId){
    return  documentService.deleteDocument(userId, documentId);
}



    private Document convertToEntity(SaveDocumentResource resource) {
        return mapper.map(resource, Document.class);
    }

    private DocumentResource convertToResource(Document entity) {
        return mapper.map(entity, DocumentResource.class);
    }


}
