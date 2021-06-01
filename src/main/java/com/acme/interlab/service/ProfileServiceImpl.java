package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Profile;
import com.acme.interlab.model.User;
import com.acme.interlab.repository.ProfileRepository;
import com.acme.interlab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Profile createProfile(Long userId, Profile profile) {
        return userRepository.findById(userId).map(user -> {
            profile.setUser(user);
            return profileRepository.save(profile);
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Profile updateProfile(Long userId, Profile profileRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        return userRepository.findById(userId).map(user -> {
            Profile profile = user.getProfile();
            profile.setFirstName(profileRequest.getFirstName());
            profile.setLastName(profileRequest.getLastName());
            profile.setEmail(profileRequest.getEmail());
            profile.setField(profileRequest.getField());
            profile.setPhone(profileRequest.getPhone());
            profile.setDescription(profileRequest.getDescription());
            profile.setCountry(profileRequest.getCountry());
            profile.setCity(profileRequest.getCity());
            if(user.getRole().equals("student")) {
                profile.setDegree(profileRequest.getDegree());
                profile.setUniversity(profileRequest.getUniversity());
                profile.setSemester(profileRequest.getSemester());
            }
            return profileRepository.save(profile);
        }).orElseThrow(() -> new ResourceNotFoundException("Profile", "userId", userId));
    }

    @Override
    public ResponseEntity<?> deleteProfile(Long userId) {
        return userRepository.findById(userId).map(user -> {
            Profile profile = user.getProfile();
            profileRepository.delete(profile);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Profile getProfileById(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", profileId));
    }

    @Override
    public Profile getProfileByUserId(Long userId) {
        return userRepository.findById(userId).map(User::getProfile).orElseThrow(() -> new ResourceNotFoundException("Profile", "userId", userId));
    }

    @Override
    public Page<Profile> getAllProfiles(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

}
