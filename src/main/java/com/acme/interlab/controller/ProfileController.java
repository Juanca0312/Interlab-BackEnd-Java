package com.acme.interlab.controller;

import com.acme.interlab.model.Profile;
import com.acme.interlab.resource.ProfileResource;
import com.acme.interlab.resource.SaveProfileResource;
import com.acme.interlab.util.UserProfile;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.acme.interlab.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "profiles", description = "Profiles API")
@RestController("Profile")
@CrossOrigin
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfileService profileService;

    //Get all profiles
    @GetMapping("/profiles")
    public Page<ProfileResource> getAllProfiles(Pageable pageable) {
        Page<Profile> profilesPage = profileService.getAllProfiles(pageable);
        List<ProfileResource> resources = profilesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    //Get profiles by id
    @GetMapping("/profiles/{id}")
    public ProfileResource getProfileById(@PathVariable(name = "id") Long profileId) {
        return convertToResource(profileService.getProfileById(profileId));
    }

    //Get by UserId
    @GetMapping("users/{userId}/profiles")
    public ProfileResource getProfileByUserId(@PathVariable(name = "userId") Long userId) {
        return convertToResource(profileService.getProfileByUserId(userId));
    }

    @GetMapping("internships/{internshipId}/profiles")
    public Page<UserProfile> getStudentProfileOfinternships(@PathVariable(name = "internshipId") Long internshipId, Pageable pageable) {
        return profileService.getAllProfilesByInternshipId(internshipId, pageable);
    }

    //Create
    @PostMapping("/users/{userId}/profiles")
    public ProfileResource createProfile(@PathVariable(name = "userId") Long userId, @Valid @RequestBody SaveProfileResource resource) {
        Profile profile = convertToEntity(resource);
        return convertToResource(profileService.createProfile(userId, profile));
    }

    //Update
    @PutMapping("users/{userId}/profiles")
    public ProfileResource updateProfile(@PathVariable(name = "userId") Long userId,
                                         @Valid @RequestBody SaveProfileResource resource) {
        return convertToResource(profileService.updateProfile(userId, convertToEntity(resource)));
    }

    //Eliminar
    @DeleteMapping("users/{userId}/profiles")
    public ResponseEntity<?> deleteProfile(@PathVariable(name = "userId") Long userId) {
        return profileService.deleteProfile(userId);
    }

    private Profile convertToEntity(SaveProfileResource resource) {
        return mapper.map(resource, Profile.class);
    }

    private ProfileResource convertToResource(Profile entity) {
        return mapper.map(entity, ProfileResource.class);
    }
}
