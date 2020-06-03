package com.acme.interlab.service;

import com.acme.interlab.model.Internship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InternshipService {
    Internship getInternshipById(Long internshipId);
    Page<Internship> getAllInternships(Pageable pageable);
    Internship createInternship(Internship internship);
    Internship updateInternship(Long internshipId, Internship internshipRequest);
    ResponseEntity<?> deletePost(Long internshipId);
}
