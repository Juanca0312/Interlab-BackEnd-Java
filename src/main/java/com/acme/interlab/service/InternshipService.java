package com.acme.interlab.service;

import com.acme.interlab.model.Internship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InternshipService {
    Page<Internship> getAllInternships(Pageable pageable);
    Page<Internship> getAllInternshipsByCompanyId(Long companyId, Pageable pageable);
    Internship getInternshipByIdAndCompanyId(Long companyId, Long internshipId);
    Internship createInternship(Long companyId, Internship internship);
    Internship updateInternship(Long companyId, Long internshipId, Internship internshipDetails);
    ResponseEntity<?> deleteInternship(Long companyId, Long internshipId);
    Page<Internship> getAllInternshipsByUserId(Long userId, Pageable pageable);
}
