package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Internship;
import com.acme.interlab.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class InternshipServiceImpl implements InternshipService {

    @Autowired
    private InternshipRepository internshipRepository;

    @Override
    public Internship createInternship(Internship internship) { return internshipRepository.save(internship); }

    @Override
    public Internship updateInternship(Long internshipId, Internship internshipRequest) {
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "Id", internshipId));
        internship.setState(internshipRequest.getState());
        internship.setDescription(internshipRequest.getDescription());
        internship.setSalary(internshipRequest.getSalary());
        internship.setStartingDate(internshipRequest.getStartingDate());
        internship.setFinishingDate(internshipRequest.getFinishingDate());
        return internshipRepository.save(internship);
    }

    @Override
    public ResponseEntity<?> deletePost(Long internshipId) {
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "Id", internshipId));
        internshipRepository.delete(internship);
        return ResponseEntity.ok().build();
    }

    @Override
    public Internship getInternshipById(Long internshipId) {
        return internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "Id", internshipId));
    }


    @Override
    public Page<Internship> getAllInternships(Pageable pageable) {
        return internshipRepository.findAll(pageable);
    }
}
