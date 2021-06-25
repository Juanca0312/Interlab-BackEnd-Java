package com.acme.interlab.service;

import com.acme.interlab.model.Internship;
import com.acme.interlab.util.InternshipStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InternshipService {
    Page<Internship> getAllInternships(Pageable pageable);
    Page<Internship> getAllActiveInternships(Pageable pageable);
    Page<Internship> getAllActiveInternshipsByCompanyId(Long companyId, Pageable pageable);
    Internship selectStudent(Long userId, Long internshipId);
    Page<InternshipStudent> getAllEndedInternships(Long companyId, Pageable pageable);
    Page<Internship> getAllInternshipsByCompanyId(Long companyId, Pageable pageable);
    Internship getInternshipByIdAndCompanyId(Long companyId, Long internshipId);
    Internship createInternship(Long companyId, Internship internship);
    Internship updateInternship(Long companyId, Long internshipId, Internship internshipDetails);
    ResponseEntity<?> deleteInternship(Long companyId, Long internshipId);
    Page<Internship> getAllInternshipsByUserId(Long userId, Pageable pageable);
}
