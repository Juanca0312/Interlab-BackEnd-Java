package com.acme.interlab.repository;

import com.acme.interlab.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Page<Profile> findByUserId(Long userId, Pageable pageable);
}
