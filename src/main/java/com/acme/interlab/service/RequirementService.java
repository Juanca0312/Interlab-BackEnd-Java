package com.acme.interlab.service;

        import com.acme.interlab.model.Profile;
        import com.acme.interlab.model.Qualification;
        import com.acme.interlab.model.Requirement;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.http.ResponseEntity;

public interface RequirementService {
    Requirement createRequirement(Long internshipId,Requirement requirement);
    Requirement updateRequirement(Long internshipId,Long requirementId, Requirement requirementRequest);
    ResponseEntity<?> deleteRequirement(Long internshipId,Long requirementId);
    Requirement getRequirementById(Long requirementId);
    Page<Requirement> getAllRequirements(Pageable pageable);
    Page<Requirement> getAllRequirementsByInternshipId(Long internshipId, Pageable pageable);

}