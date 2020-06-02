package com.acme.interlab.repository;

import com.acme.interlab.model.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification,Long> {
//    Page<Qualification> findById(int id, Pageable pageable);
//    Optional<Qualification> findById(int id);
}
