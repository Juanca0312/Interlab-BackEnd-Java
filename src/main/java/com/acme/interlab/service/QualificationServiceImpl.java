package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.*;
import com.acme.interlab.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Qualification getQualificationById(Long qualificationId) {
        return qualificationRepository.findById(qualificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Qualification", "Id", qualificationId));
    }


    @Override
    public Page<Qualification> getAllQualifications(Pageable pageable) {
        return qualificationRepository.findAll(pageable);
    }
}
