package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Profile;
import com.acme.interlab.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile createProfile(Profile profile) { return profileRepository.save(profile); }

    @Override
    public Profile updateProfile(Long profileId, Profile profileRequest) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", profileId));
        profile.setField(profileRequest.getField());
        profile.setSemester(profileRequest.getSemester());
        profile.setDegree(profileRequest.getDegree());
        profile.setDescription(profileRequest.getDescription());
        return profileRepository.save(profile);
    }

    @Override
    public ResponseEntity<?> deletePost(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", profileId));
        profileRepository.delete(profile);
        return ResponseEntity.ok().build();
    }

    @Override
    public Profile getProfileById(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", profileId));
    }

    @Override
    public Page<Profile> getAllPosts(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }
}
