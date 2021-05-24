package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.*;
import com.acme.interlab.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QualificationServiceImpl implements QualificationService{

    @Autowired
    private QualificationRepository qualificationRepository;

    @Override
    public Qualification createQualification(Qualification qualification) { return qualificationRepository.save(qualification); }

    @Override
    public Qualification updateQualification(Long id, Qualification qualificationRequest) {
        Qualification qualification = qualificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Qualification", "Id", id));
        qualification.setScore(qualificationRequest.getScore());
        qualification.setComment(qualificationRequest.getComment());
        qualification.setAuthor(qualificationRequest.getAuthor());
        return qualificationRepository.save(qualification);
    }

    @Override
    public ResponseEntity<?> deleteQualification(Long qualificationId) {
        Qualification qualification = qualificationRepository.findById(qualificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Qualification", "Id", qualificationId));
        qualificationRepository.delete(qualification);
        return ResponseEntity.ok().build();
    }

    @Override
    public Qualification getQualificationByIdAndCompanyId(Long qualificationId, Long companyId) {
        return qualificationRepository.findByIdAndCompanyId(qualificationId,companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Qualification not found with ID "+qualificationId +
                        " and CompanyId" +companyId));
    }

    @Override
    public Qualification getQualificationByIdAndUserId(Long qualificationId, Long userId) {
        return qualificationRepository.findByIdAndCompanyId(qualificationId,userId)
                .orElseThrow(() -> new ResourceNotFoundException("Qualification not found with ID "+qualificationId +
                        " and UserId" +userId));
    }

    @Override
    public Page<Qualification> getAllQualificationsByCompanyId(Long companyId, Pageable pageable) {
        return qualificationRepository.findByCompanyId(companyId, pageable);
    }

    @Override
    public Page<Qualification> getAllQualificationsByUserId(Long userId, Pageable pageable) {
        return qualificationRepository.findByUserId(userId, pageable);
    }
}