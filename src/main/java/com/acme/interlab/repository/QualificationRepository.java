package com.acme.interlab.repository;

import com.acme.interlab.model.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification,Long> {
    Page<Qualification> findByCompanyId(Long companyId, Pageable pageable);
    Page<Qualification> findByUserId(Long userId, Pageable pageable);
    Optional<Qualification> findByIdAndUserId(Long id, Long userId);
    Optional<Qualification> findByIdAndCompanyId(Long id, Long companyId);
}


