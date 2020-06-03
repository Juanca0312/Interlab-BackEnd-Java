package com.acme.interlab.controller;

import com.acme.interlab.model.Profile;
import com.acme.interlab.resource.ProfileResource;
import com.acme.interlab.resource.SaveProfileResource;
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


@RestController
@RequestMapping("/api")
public class ProfileController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProfileService profileService;

    @GetMapping("/profiles")
    public Page<ProfileResource> getAllProfiles(Pageable pageable){
        Page<Profile> profilesPage = profileService.getAllPosts(pageable);
        List<ProfileResource> resources = profilesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/profiles/{id}")
    public ProfileResource getProfileById(@PathVariable(name="id") Long profileId){
        return convertToResource(profileService.getProfileById(profileId));
    }

    @PostMapping("/profiles")
    public ProfileResource createProfile(@Valid @RequestBody SaveProfileResource resource){
        Profile profile = convertToEntity(resource);
        return convertToResource(profileService.createProfile(profile));
    }

    @PutMapping("/profiles/{id}")
    public ProfileResource updateProfile(@PathVariable(name = "id") Long profileId,
                                         @Valid @RequestBody SaveProfileResource resource){
        Profile profile = convertToEntity(resource);
        return convertToResource(profileService.updateProfile(profileId, profile));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable(name = "id") Long profileId){
        return profileService.deletePost(profileId);
    }

    private Profile convertToEntity(SaveProfileResource resource) { return mapper.map(resource, Profile.class); }

    private ProfileResource convertToResource(Profile entity) { return mapper.map(entity, ProfileResource.class); }
}
