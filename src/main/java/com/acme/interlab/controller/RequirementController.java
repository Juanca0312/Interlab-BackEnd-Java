package com.acme.interlab.controller;

import com.acme.interlab.model.Requirement;
import com.acme.interlab.resource.RequirementResource;
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

    @GetMapping("/requirements/{id}")
    public RequirementResource getRequirementById(
            @PathVariable(name = "id") Long id) {
        return convertToResource(requirementService.getRequirementById(id));
    }


    @PostMapping("/requirements")
    public RequirementResource createPost(@Valid @RequestBody SaveRequirementResource resource)  {
        Requirement requirement = convertToEntity(resource);
        return convertToResource(requirementService.createRequirement(requirement));
    }

    @PutMapping("/requirement/{id}")
    public RequirementResource updatePost(@PathVariable(name = "id") Long id, @Valid @RequestBody SaveRequirementResource resource) {
        Requirement requirement = convertToEntity(resource);
        return convertToResource(requirementService.updateRequirement(id, requirement));
    }

    @DeleteMapping("/requirements/{id}")
    public ResponseEntity<?> deleteRequirement(@PathVariable(name = "id") Long id) {
        return requirementService.deleteRequirement(id);
    }






    private Requirement convertToEntity(SaveRequirementResource resource) {
        return mapper.map(resource, Requirement.class);
    }

    private RequirementResource convertToResource(Requirement entity) {
        return mapper.map(entity, RequirementResource.class);
    }

}
