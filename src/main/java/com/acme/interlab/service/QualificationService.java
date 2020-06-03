package com.acme.interlab.service;

import com.acme.interlab.model.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface QualificationService {
    Qualification createQualification(Qualification qualification);
    Qualification updateQualification(Long qualificationId, Qualification qualificationRequest);
    ResponseEntity<?> deleteQualification(Long qualificationId);
    Qualification getQualificationById(Long qualificationId);
    Page<Qualification> getAllQualifications(Pageable pageable);
}
