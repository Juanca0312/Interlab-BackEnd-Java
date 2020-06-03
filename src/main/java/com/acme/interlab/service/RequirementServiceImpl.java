package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Qualification;
import com.acme.interlab.model.Requirement;
import com.acme.interlab.repository.QualificationRepository;
import com.acme.interlab.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RequirementServiceImpl implements RequirementService{
    @Autowired
    private RequirementRepository requirementRepository;

    @Override
    public Requirement createRequirement(Requirement requirement) { return requirementRepository.save(requirement); }

    @Override
    public Requirement updateRequirement(Long id, Requirement requirementRequest) {
        Requirement requirement = requirementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Qualification", "Id", id));
        requirement.setDegree(requirementRequest.getDegree());
        requirement.setDescription(requirementRequest.getDescription());
        requirement.setField(requirementRequest.getField());
        requirement.setSemester(requirementRequest.getSemester());
        return requirementRepository.save(requirement);
    }

    @Override
    public ResponseEntity<?> deleteRequirement(Long requirementId) {
        Requirement requirement = requirementRepository.findById(requirementId)
                .orElseThrow(() -> new ResourceNotFoundException("Requirement", "Id", requirementId));
        requirementRepository.delete(requirement);
        return ResponseEntity.ok().build();
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
}
