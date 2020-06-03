package com.acme.interlab.service;

import com.acme.interlab.model.Qualification;
import com.acme.interlab.model.Requirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RequirementService {
    Requirement createRequirement(Requirement requirement);
    Requirement updateRequirement(Long requirementId, Requirement requirementRequest);
    ResponseEntity<?> deleteRequirement(Long requirementId);
    Requirement getRequirementById(Long requirementId);
    Page<Requirement> getAllRequirements(Pageable pageable);
}
