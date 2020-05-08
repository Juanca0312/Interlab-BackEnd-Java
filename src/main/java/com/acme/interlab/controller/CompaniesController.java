package com.acme.interlab.controller;

import com.acme.interlab.model.Company;

import com.acme.interlab.resource.CompanyResource;
import com.acme.interlab.resource.SaveCompanyResource;
import com.acme.interlab.service.CompanyService;

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
@RequestMapping(value ="/api")
public class CompaniesController {


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public Page<CompanyResource> getAllCompanies(Pageable pageable) {
        Page<Company> companiesPage = companyService.getAllCompanies(pageable);
        List<CompanyResource> resources = companiesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    @GetMapping("/companies/{id}")
    public CompanyResource getCompanyById(
            @PathVariable(name = "id") Long companyId) {
        return convertToResource(companyService.getCompanyById(companyId));
    }

    @PostMapping("/companies")
    public CompanyResource createcompamy(@Valid @RequestBody SaveCompanyResource resource)  {
        Company company = convertToEntity(resource);
        return convertToResource(companyService.createCompany(company));
    }

    @PutMapping("/companies/{id}")
    public CompanyResource updateCompany(@PathVariable(name = "id") Long companyId, @Valid @RequestBody SaveCompanyResource resource) {
        Company company = convertToEntity(resource);
        return convertToResource(companyService.updateCompany(companyId, company));
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable(name = "id") Long companyId) {
        return companyService.deleteCompany(companyId);
    }

    //Relation with Users (ManyToMany)
    @GetMapping("/companies")
    public Page<CompanyResource> getCompanyById(@PathVariable(name = "UserId") Long userId, Pageable pageable) {
        Page<Company> companiesPage = companyService.getAllCompaniesByUserId(userId, pageable);
        List<CompanyResource> resources = companiesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/companies/{companyId}/users/{userId}")
    public CompanyResource assigncompanyUser(@PathVariable(name = "companyId") Long companyId,
                                             @PathVariable(name = "userId") Long userId) {
        return convertToResource(companyService.assignCompanyUser(companyId, userId));
    }

    @DeleteMapping("/companies/{companyId}/users/{userId}")
    public CompanyResource unassignCompanyUser(@PathVariable(name = "companyId") Long companyId,
                                               @PathVariable(name = "userId") Long userId) {

        return convertToResource(companyService.unassignCompanyUser(companyId, userId));
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
