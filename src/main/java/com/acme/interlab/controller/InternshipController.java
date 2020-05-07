package com.acme.interlab.controller;

import com.acme.interlab.model.Internship;
import com.acme.interlab.resource.InternshipResource;
import com.acme.interlab.resource.SaveInternshipResource;
import com.acme.interlab.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/api")
public class InternshipController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private InternshipService internshipService;

    @GetMapping("/internships")
    public Page<InternshipResource> getAllProfiles(Pageable pageable){
        Page<Internship> internshipsPage = internshipService.getAllPosts(pageable);
        List<InternshipResource> resources = internshipsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/internships/{id}")
    public InternshipResource getInternshipById(@PathVariable(name="id") Long internshipId){
        return convertToResource(internshipService.getInternshipById(internshipId));
    }


    @PostMapping("/internships")
    public InternshipResource createInternship(@Valid @RequestBody SaveInternshipResource resource){
        Internship internship = convertToEntity(resource);
        return convertToResource(internshipService.createInternship(internship));
    }

    @PutMapping("/internships/{id}")
    public InternshipResource updateInternship(@PathVariable(name = "id") Long internshipId,
                                         @Valid @RequestBody SaveInternshipResource resource){
        Internship internship = convertToEntity(resource);
        return convertToResource(internshipService.updateInternship(internshipId, internship));
    }

    @DeleteMapping("/internships/{id}")
    public ResponseEntity<?> deleteInternship(@PathVariable(name = "id") Long internshipId){
        return internshipService.deletePost(internshipId);
    }

    private Internship convertToEntity(SaveInternshipResource resource) { return mapper.map(resource, Internship.class); }

    private InternshipResource convertToResource(Internship entity) { return mapper.map(entity, InternshipResource.class); }

}
