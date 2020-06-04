package com.acme.interlab.controller;

import com.acme.interlab.model.Profile;
import com.acme.interlab.model.Requirement;
import com.acme.interlab.resource.ProfileResource;
import com.acme.interlab.resource.RequirementResource;
import com.acme.interlab.resource.SaveProfileResource;
import com.acme.interlab.resource.SaveRequirementResource;
import com.acme.interlab.service.RequirementService;
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
public class RequirementController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RequirementService requirementService;

    public Page<RequirementResource> getAllRequirements(Pageable pageable) {
        Page<Requirement> requirementPage = requirementService.getAllRequirements(pageable);
        List<RequirementResource> resources = requirementPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("internships/{internshipId}/requirement")
    public Page<RequirementResource> getRequirementsByInternshipId(@PathVariable(name = "internshipId") Long internshipId, Pageable pageable) {
        Page<Requirement> requirementPage = requirementService.getAllRequirementsByInternshipId(internshipId, pageable);
        List<RequirementResource> resources = requirementPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/requirements/{id}")
    public RequirementResource getRequirementById(
            @PathVariable(name = "id") Long id) {
        return convertToResource(requirementService.getRequirementById(id));
    }


    @PostMapping("/internships/{internshipId}/requirements")
    public RequirementResource createRequirement(@PathVariable(name = "internshipId") Long internshipId, @Valid @RequestBody SaveRequirementResource resource) {
        Requirement requirement = convertToEntity(resource);
        return convertToResource(requirementService.createRequirement(internshipId, requirement));
    }

    @PutMapping("internships/{internshipId}/requirements/{id}")
    public RequirementResource updateRequirement(@PathVariable(name = "internshipId") Long internshipId,
                                         @PathVariable(name = "id") Long requirementId,
                                         @Valid @RequestBody SaveRequirementResource resource) {
        return convertToResource(requirementService.updateRequirement(internshipId, requirementId, convertToEntity(resource)));
    }

    @DeleteMapping("internships/{internshipId}/requirements/{id}")
    public ResponseEntity<?> deleteRequirement(@PathVariable(name = "internshipId") Long internshipId,
                                           @PathVariable(name = "id") Long requirementId) {
        return requirementService.deleteRequirement(internshipId, requirementId);
    }




    private Requirement convertToEntity(SaveRequirementResource resource) {
        return mapper.map(resource, Requirement.class);
    }

    private RequirementResource convertToResource(Requirement entity) {
        return mapper.map(entity, RequirementResource.class);
    }

}
