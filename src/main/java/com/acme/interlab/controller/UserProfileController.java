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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserProfileController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProfileService profileService;

    @GetMapping("users/{userId}/profiles")
    public Page<ProfileResource> getProfileByUserId(@PathVariable(name="userId") Long userId, Pageable pageable) {
        Page<Profile> profilePage = profileService.getAllProfilesByUserId(userId, pageable);
        List<ProfileResource> resources = profilePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Profile convertToEntity(SaveProfileResource resource) { return mapper.map(resource, Profile.class); }

    private ProfileResource convertToResource(Profile entity) { return mapper.map(entity, ProfileResource.class); }


}
