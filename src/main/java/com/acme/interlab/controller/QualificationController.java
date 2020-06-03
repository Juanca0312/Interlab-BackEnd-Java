package com.acme.interlab.controller;

import com.acme.interlab.model.Qualification;
import com.acme.interlab.resource.QualificationResource;
import com.acme.interlab.resource.SaveQualificationResource;
import com.acme.interlab.service.QualificationService;
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
public class QualificationController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private QualificationService qualificationService;

    public Page<QualificationResource> getAllQualificationsByCompanyId(@PathVariable(name = "companyId") Long companyId,
                                                                       Pageable pageable) {
        Page<Qualification> qualificationPage = qualificationService.getAllQualificationsByCompanyId(companyId, pageable);
        List<QualificationResource> resources = qualificationPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    public Page<QualificationResource> getAllQualificationsByUserId(@PathVariable(name = "userId") Long userId,
                                                                    Pageable pageable) {
        Page<Qualification> qualificationPage = qualificationService.getAllQualificationsByUserId(userId, pageable);
        List<QualificationResource> resources = qualificationPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    //editado
    @GetMapping("/qualifications/{id}")
    public QualificationResource getQualificationByIdAndCompanyId(
            @PathVariable(name = "companyId") Long companyId,
            @PathVariable(name = "qualificationId") Long qualificationId) {
        return convertToResource(qualificationService.getQualificationByIdAndCompanyId(companyId,qualificationId));
    }
    //editado
    @GetMapping("/qualifications/{id}")
    public QualificationResource getQualificationByIdAndUserId(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "qualificationId") Long qualificationId) {
        return convertToResource(qualificationService.getQualificationByIdAndUserId(userId,qualificationId));
    }

    @PostMapping("/qualifications")
    public QualificationResource createPost(@Valid @RequestBody SaveQualificationResource resource)  {
        Qualification qualification = convertToEntity(resource);
        return convertToResource(qualificationService.createQualification(qualification));
    }

    @PutMapping("/qualification/{id}")
    public QualificationResource updatePost(@PathVariable(name = "id") Long id, @Valid @RequestBody SaveQualificationResource resource) {
        Qualification qualification = convertToEntity(resource);
        return convertToResource(qualificationService.updateQualification(id, qualification));
    }

    @DeleteMapping("/qualifications/{id}")
    public ResponseEntity<?> deleteQualification(@PathVariable(name = "id") Long id) {
        return qualificationService.deleteQualification(id);
    }


    private Qualification convertToEntity(SaveQualificationResource resource) {
        return mapper.map(resource, Qualification.class);
    }

    private QualificationResource convertToResource(Qualification entity) {
        return mapper.map(entity, QualificationResource.class);
    }

}