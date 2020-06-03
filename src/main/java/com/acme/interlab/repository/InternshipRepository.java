package com.acme.interlab.repository;

import com.acme.interlab.model.Internship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {
    Page<Internship> findByCompanyId(Long internshipId, Pageable pageable);
    Optional<Internship> findByIdAndCompanyId(Long id, Long CompanyId);
}
