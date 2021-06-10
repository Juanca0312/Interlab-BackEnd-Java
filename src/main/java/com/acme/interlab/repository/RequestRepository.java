package com.acme.interlab.repository;

import com.acme.interlab.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Page<Request> findByUserId(Long userId, Pageable pageable);
    Optional<Request> findByIdAndUserIdAndInternshipId(Long id, Long userId, Long internshipId);
    Page<Request> findByUserIdAndState(Long userId, String state, Pageable pageable);
}
