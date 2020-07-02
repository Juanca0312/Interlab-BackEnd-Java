package com.acme.interlab.controller;

import com.acme.interlab.model.Internship;
import com.acme.interlab.resource.InternshipResource;
import com.acme.interlab.resource.SaveInternshipResource;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping(value ="/api")
public class InternshipController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private InternshipService internshipService;

    @GetMapping("companies/{companyId}/internships")
    public Page<InternshipResource> getAllInternshipsByCompanyId(@PathVariable(name = "companyId") Long companyId,
                                                                Pageable pageable){
        Page<Internship> internshipsPage = internshipService.getAllInternshipsByCompanyId(companyId, pageable);
        List<InternshipResource> resources = internshipsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("companies/{companyId}/internships/{id}")
    public InternshipResource getInternshipByIdAndCompanyId(@PathVariable(name = "companyId") Long companyId,
                                                            @PathVariable(name="id") Long internshipId){
        return convertToResource(internshipService.getInternshipByIdAndCompanyId(companyId, internshipId));
    }

    @PostMapping("companies/{companyId}/internships")
    public InternshipResource createInternship(@PathVariable(name = "companyId") Long companyId,
                                                @Valid @RequestBody SaveInternshipResource resource){
        return convertToResource(internshipService.createInternship(companyId, convertToEntity(resource)));
    }

    @PutMapping("companies/{companyId}/internships/{id}")
    public InternshipResource updateInternship(@PathVariable(name = "companyId") Long companyId,
                                                @PathVariable(name = "id") Long internshipId,
                                                @Valid @RequestBody SaveInternshipResource resource){
        return convertToResource(internshipService.updateInternship(companyId, internshipId, convertToEntity(resource)));
    }

    @DeleteMapping("companies/{companyId}/internships/{id}")
    public ResponseEntity<?> deleteInternship(@PathVariable(name = "companyId") Long companyId,
                                                @PathVariable(name = "id") Long internshipId){
        return internshipService.deleteInternship(companyId, internshipId);
    }

    private Internship convertToEntity(SaveInternshipResource resource) { return mapper.map(resource, Internship.class); }

    private InternshipResource convertToResource(Internship entity) { return mapper.map(entity, InternshipResource.class); }

}
