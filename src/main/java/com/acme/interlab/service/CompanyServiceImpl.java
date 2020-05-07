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
public class CompanyServiceImpl implements  CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "Id", companyId));
        companyRepository.delete(company);
        return ResponseEntity.ok().build();
    }

    @Override
    public Company updateCompany(Long companyId, Company companyRequest) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "Id", companyId));
        company.setName(companyRequest.getName());
        company.setDescription(companyRequest.getDescription());
        company.setSector(companyRequest.getSector());
        company.setMail(companyRequest.getMail());
        company.setPhone_number(companyRequest.getPhone_number());
        company.setAddress(companyRequest.getAddress());
        company.setCountry(companyRequest.getCountry());
        company.setCity(companyRequest.getCity());
        return companyRepository.save(company);
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("company", "Id", companyId));
    }

    @Override
    public Page<Company> getAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    /*
    @Override
    public Company assignCompnyUser(Long companyId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        return companyRepository.findById(companyId).map(company ->{
            if(!company.getUsers().contains(user)){
                company.getUsers().add(user);
            }
            return company;
        }).orElseThrow(() -> new ResourceNotFoundException("Company", "Id", companyId));
    }

    @Override
    public Company unassignCompnyUser(Long companyId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return companyRepository.findById(companyId).map(company -> {
            company.getUsers().remove(user);
            return companyRepository.save(company);
        }).orElseThrow(() -> new ResourceNotFoundException("Company", "Id", companyId));
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

    */

}
