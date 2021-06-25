package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Internship;
import com.acme.interlab.model.Profile;
import com.acme.interlab.model.Request;
import com.acme.interlab.model.User;
import com.acme.interlab.repository.InternshipRepository;
import com.acme.interlab.repository.ProfileRepository;
import com.acme.interlab.repository.RequestRepository;
import com.acme.interlab.repository.UserRepository;
import com.acme.interlab.util.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private InternshipRepository internshipRepository;

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

    @Override
    public Page<UserProfile> getAllProfilesByInternshipId(Long internshipId, Pageable pageable) {
        Internship internship = internshipRepository.findById(internshipId).orElseThrow(() -> new ResourceNotFoundException("internship", "internship", internshipId));
        List<Request> requests = internship.getRequests();
        List<UserProfile> profiles = new ArrayList<>();

        for(Request request: requests){
            User user = request.getUser();
            UserProfile profile = new UserProfile();

            profile.setUserId(user.getId());
            profile.setUsername(user.getUsername());
            profile.setPassword(user.getPassword());
            profile.setRole(user.getRole());
            profile.setProfileId(user.getProfile().getId());
            profile.setFirstName(user.getProfile().getFirstName());
            profile.setLastName(user.getProfile().getLastName());
            profile.setField(user.getProfile().getField());
            profile.setPhone(user.getProfile().getPhone());
            profile.setEmail(user.getProfile().getEmail());
            profile.setDescription(user.getProfile().getDescription());
            profile.setCountry(user.getProfile().getCountry());
            profile.setCity(user.getProfile().getCity());
            profile.setUniversity(user.getProfile().getUniversity());
            profile.setDegree(user.getProfile().getDegree());
            profile.setSemester(user.getProfile().getSemester());


            profiles.add(profile);
        }

        int internshipsCount = profiles.size();

        return new PageImpl<>(profiles, pageable, internshipsCount);
    }

}
