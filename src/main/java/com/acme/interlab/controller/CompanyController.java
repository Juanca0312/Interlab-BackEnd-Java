package com.acme.interlab.controller;

import com.acme.interlab.model.Company;

import com.acme.interlab.resource.CompanyResource;
import com.acme.interlab.resource.SaveCompanyResource;
import com.acme.interlab.service.CompanyService;

import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "companies", description = "Companies API")
@RestController
@RequestMapping(value ="/api")
public class CompanyController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public Page<CompanyResource> getAllCompanies(Pageable pageable) {
        List<CompanyResource> companies = companyService.getAllCompanies(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int companiesCount = companies.size();
        return new PageImpl<>(companies, pageable, companiesCount);
    }

    @GetMapping("/companies/{id}")
    public CompanyResource getCompanyById(@PathVariable(name = "id") Long companyId) {
        return convertToResource(companyService.getCompanyById(companyId));
    }

    @GetMapping("/users/{userId}/companies")
    public Page<CompanyResource> getAllCompaniesByUserId(@PathVariable(name = "userId") Long userId, Pageable pageable) {
        List<CompanyResource> companies = companyService.getAllCompaniesByUserId(userId, pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int companyCount = companies.size();
        return new PageImpl<>(companies, pageable, companyCount);
    }

    @PostMapping("/companies")
    public CompanyResource createCompany(@Valid @RequestBody SaveCompanyResource resource) {
        return convertToResource(companyService.createCompany(convertToEntity(resource)));
    }

    @PutMapping("/companies/{id}")
    public CompanyResource updateCompany(@PathVariable(name = "id") Long companyId, @Valid @RequestBody SaveCompanyResource resource) {
        return convertToResource(companyService.updateCompany(companyId, convertToEntity(resource)));
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable(name = "id") Long companyId) {
        return companyService.deleteCompany(companyId);
    }

    private Company convertToEntity(SaveCompanyResource resource)
    {
        return  mapper.map(resource, Company.class);
    }

    private CompanyResource convertToResource(Company entity)
    {
        return mapper.map(entity, CompanyResource.class);
    }

}
