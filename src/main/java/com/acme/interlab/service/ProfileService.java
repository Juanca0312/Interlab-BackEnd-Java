package com.acme.interlab.service;

import com.acme.interlab.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    Profile createProfile(Profile profile);
    Profile updateProfile(Long profileId, Profile profileRequest);
    ResponseEntity<?> deletePost(Long profileId);
    Profile getProfileById(Long profileId);
    Page<Profile> getAllProfiles(Pageable pageable);
    Page<Profile> getAllProfilesByUserId(Long userId, Pageable pageable);

}