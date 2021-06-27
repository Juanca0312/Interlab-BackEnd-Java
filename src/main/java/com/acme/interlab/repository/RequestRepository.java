package com.acme.interlab.repository;

import com.acme.interlab.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Page<Request> findByUserId(Long userId, Pageable pageable);
    Boolean existsByUserIdAndInternshipId(Long userId, Long internshipId);
    List<Request> findByUserId(Long userId);
    Optional<Request> findByIdAndUserIdAndInternshipId(Long id, Long userId, Long internshipId);
    Page<Request> findByUserIdAndState(Long userId, String state, Pageable pageable);
}
