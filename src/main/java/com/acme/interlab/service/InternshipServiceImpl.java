package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Internship;
import com.acme.interlab.repository.CompanyRepository;
import com.acme.interlab.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class InternshipServiceImpl implements InternshipService {

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Page<Internship> getAllInternshipsByCompanyId(Long companyId, Pageable pageable) {
        return internshipRepository.findByCompanyId(companyId, pageable);
    }

    @Override
    public Internship getInternshipByIdAndCompanyId(Long companyId, Long internshipId) {
        return internshipRepository.findByIdAndCompanyId(internshipId, companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Internship not found with Id " + internshipId +
                                " and Company Id " + companyId));
    }

    @Override
    public Internship createInternship(Long companyId, Internship internship) {
        return companyRepository.findById(companyId).map(company -> {
            internship.setCompany(company);
            return internshipRepository.save(internship);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Company", "Id", companyId));
    }

    @Override
    public Internship updateInternship(Long companyId, Long internshipId, Internship internshipDetails) {
        if(!companyRepository.existsById(companyId)) //existe?
            throw new ResourceNotFoundException("Company", "Id", companyId);

        return internshipRepository.findById(internshipId).map(internship -> {
            internship.setState(internshipDetails.getState());
            internship.setDescription(internshipDetails.getDescription());
            internship.setSalary(internshipDetails.getSalary());
            return internshipRepository.save(internship);
        }).orElseThrow(() -> new ResourceNotFoundException("Internship", "Id", internshipId));

    }

    @Override
    public ResponseEntity<?> deleteInternship(Long companyId, Long internshipId) {
        return internshipRepository.findByIdAndCompanyId(internshipId, companyId).map(internship -> {
            internshipRepository.delete(internship);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Internship not found with Id " + internshipId + " and CompanyId " + companyId));

    }
}
