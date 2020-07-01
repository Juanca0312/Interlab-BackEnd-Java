package com.acme.interlab.service;

import com.acme.interlab.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CompanyService {

    Page<Company> getAllCompanies(Pageable pageable);
    Page<Company> getAllCompaniesByUserId(Long userId, Pageable pageable);

    Company getCompanyById(Long companyId);
    Company createCompany(Company company);
    Company updateCompany(Long companyId, Company companyRequest);
    ResponseEntity<?> deleteCompany(Long companyId);

}
