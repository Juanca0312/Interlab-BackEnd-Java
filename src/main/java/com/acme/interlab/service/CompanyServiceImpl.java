package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Company;
import com.acme.interlab.model.User;
import com.acme.interlab.repository.CompanyRepository;
import com.acme.interlab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Company> getAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public Page<Company> getAllCompaniesByUserId(Long userId, Pageable pageable) {
        return userRepository.findById(userId).map(user -> {
            List<Company> companies = user.getCompanies();
            int companiesCount = companies.size();
            return new PageImpl<>(companies, pageable, companiesCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("company", "Id", companyId));
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Long companyId, Company companyDetails) {
        return companyRepository.findById(companyId).map(company -> {
            company.setName(companyDetails.getName());
            company.setDescription(companyDetails.getDescription());
            company.setSector(companyDetails.getSector());
            company.setEmail(companyDetails.getEmail());
            company.setPhone(companyDetails.getPhone());
            company.setAddress(companyDetails.getAddress());
            company.setCountry(companyDetails.getCountry());
            company.setCity(companyDetails.getCity());
            return companyRepository.save(company);
        }).orElseThrow(() -> new ResourceNotFoundException("Company", "Id", companyId));
    }

    @Override
    public ResponseEntity<?> deleteCompany(Long companyId) {
        return companyRepository.findById(companyId).map(company -> {
            companyRepository.delete(company);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Company", "Id", companyId));
    }

}
