package com.acme.interlab.repository;

import com.acme.interlab.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Page<Document> findByUserId(Long userId, Pageable pageable);
    Optional<Document> findByIdAndUserId(Long id, Long userId);

}
