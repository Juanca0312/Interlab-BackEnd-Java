package com.acme.interlab.service;

import com.acme.interlab.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CompanyService {

    ResponseEntity<?> deleteCompany(Long companyId);
    Company updateCompany(Long companyId, Company companyRequest);
    Company createCompany(Company company);
    Company getCompanyById(Long companyId);
    Page<Company> getAllCompanies(Pageable pageable);

    //Methos with Users
    //Company assignCompanyUser(Long companyId, Long userId);
    //Company unassignCompanyUser(Long companyId, Long userId);
    //Page<Company> getAllCompaniesByUserId(Long userId, org.springframework.data.domain.Pageable pageable);
}
