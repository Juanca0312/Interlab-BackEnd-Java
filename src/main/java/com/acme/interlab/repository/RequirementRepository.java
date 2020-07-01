package com.acme.interlab.repository;

import com.acme.interlab.model.Profile;
import com.acme.interlab.model.Requirement;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement,Long> {
    Page<Requirement> findByInternshipId(Long internshipId, Pageable pageable);
    Optional<Requirement> findByIdAndInternshipId(Long id, Long internshipId);
}
