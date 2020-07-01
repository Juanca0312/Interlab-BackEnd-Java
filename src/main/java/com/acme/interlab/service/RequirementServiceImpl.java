package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Internship;
import com.acme.interlab.model.Qualification;
import com.acme.interlab.model.Requirement;
import com.acme.interlab.repository.InternshipRepository;
import com.acme.interlab.repository.QualificationRepository;
import com.acme.interlab.repository.RequirementRepository;
import com.acme.interlab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RequirementServiceImpl implements RequirementService{
    @Autowired
    private RequirementRepository requirementRepository;
    @Autowired
    private InternshipRepository internshipRepository;
    @Override
    public Requirement createRequirement(Long internshipId, Requirement requirement) {
        return internshipRepository.findById(internshipId).map(internship -> {
            requirement.setInternship(internship);
            return requirementRepository.save(requirement);
        }).orElseThrow(() -> new ResourceNotFoundException("Internship", "Id", internshipId));
    }

    @Override
    public Requirement updateRequirement(Long internshipId, Long requirementId, Requirement requirementRequest) {
        if(!internshipRepository.existsById(internshipId)) {
            throw new ResourceNotFoundException("Internship", "Id", internshipId);
        }

        return requirementRepository.findById(requirementId).map(requirement -> {
            requirement.setSemester(requirementRequest.getSemester());
            requirement.setField(requirementRequest.getField());
            requirement.setDescription(requirementRequest.getDescription());
            requirement.setDegree(requirementRequest.getDegree());
            return requirementRepository.save(requirement);
        }).orElseThrow(() -> new ResourceNotFoundException("Requirement", "Id", requirementId));

    }

    @Override
    public ResponseEntity<?> deleteRequirement(Long internshipId, Long requirementId) {
        return requirementRepository.findByIdAndInternshipId(requirementId, internshipId).map(requirement -> {
            requirementRepository.delete(requirement);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Requirement", "Id", requirementId));

    }

    @Override
    public Requirement getRequirementById(Long requirementId) {
        return requirementRepository.findById(requirementId)
                .orElseThrow(() -> new ResourceNotFoundException("Requirement", "Id", requirementId));
    }


    @Override
    public Page<Requirement> getAllRequirements(Pageable pageable) {
        return requirementRepository.findAll(pageable);
    }

    @Override
    public Page<Requirement> getAllRequirementsByInternshipId(Long internshipId, Pageable pageable) {
        return requirementRepository.findByInternshipId(internshipId, pageable);
    }
}
