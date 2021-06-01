package com.acme.interlab.service;

import com.acme.interlab.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    Profile createProfile(Long userId, Profile profile);
    Profile updateProfile(Long userId, Profile profileRequest);
    ResponseEntity<?> deleteProfile(Long userId);
    Profile getProfileById(Long profileId);
    Profile getProfileByUserId(Long userId);
    Page<Profile> getAllProfiles(Pageable pageable);
}