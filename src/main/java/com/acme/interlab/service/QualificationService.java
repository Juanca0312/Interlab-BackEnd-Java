package com.acme.interlab.service;

import com.acme.interlab.model.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface QualificationService {
    Qualification createQualification(Qualification qualification);
    Qualification updateQualification(Long qualificationId, Qualification qualificationRequest);
    ResponseEntity<?> deleteQualification(Long qualificationId);
    Qualification getQualificationByIdAndCompanyId(Long company,Long qualificationId);
    Qualification getQualificationByIdAndUserId(Long userId, Long qualificationId);
    Page<Qualification> getAllQualificationsByCompanyId(Long companyId, Pageable pageable);
    Page<Qualification> getAllQualificationsByUserId(Long userId,Pageable pageable);
}