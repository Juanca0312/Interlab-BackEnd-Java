package com.acme.interlab.repository;

import com.acme.interlab.model.Requests;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestsRepository extends JpaRepository<Requests, Long> {
    Page<Requests> findByUserId(Long userId, Pageable pageable);
    Optional<Requests> findByIdAndUserId(Long id, Long userId);
}
