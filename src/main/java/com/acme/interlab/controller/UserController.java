package com.acme.interlab.controller;

import com.acme.interlab.model.User;
import com.acme.interlab.resource.SaveUserResource;
import com.acme.interlab.resource.UserResource;
import com.acme.interlab.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "user", description = "Users API")
@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable){
        Page<User> usersPage = userService.getAllUsers(pageable);
        List<UserResource> resources = usersPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/users/{id}")
    public UserResource getUserById(@PathVariable(name = "id") Long userId){
        return convertToResource(userService.getUserById(userId));
    }

    @GetMapping("/companies/{companyId}/users")
    public Page<UserResource> getAllUsersByCompanyId(@PathVariable(name = "companyId") Long companyId, Pageable pageable){
        Page<User> usersPage = userService.getAllUsersByCompanyId(companyId, pageable);
        List<UserResource> resources = usersPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/users")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource)  {
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user));
    }

    @PutMapping("/users/{id}")
    public UserResource updateUser(@PathVariable(name = "id") Long userId, @Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(userId, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long userId) {
        return userService.deleteUser(userId);
    }

    @PostMapping("/users/{userId}/companies/{companyId}")
    public UserResource assignUserCompany(@PathVariable(name = "userId") Long userId,
                                          @PathVariable(name = "companyId") Long companyId) {
        return convertToResource(userService.assignUserCompany(userId, companyId));
    }

    @DeleteMapping("/users/{userId}/companies/{companyId}")
    public UserResource unAssignUserCompany(@PathVariable(name = "userId") Long userId,
                                        @PathVariable(name = "companyId") Long companyId) {

        return convertToResource(userService.unAssignUserCompany(userId, companyId));
    }

    @PostMapping("/users/{userId}/internships/{internshipId}")
    public UserResource assignUserInternship(@PathVariable(name = "userId") Long userId,
                                          @PathVariable(name = "internshipId") Long internshipId) {
        return convertToResource(userService.assignUserInternship(userId, internshipId));
    }

    @DeleteMapping("/users/{userId}/internship/{internshipId}/request/{requestId}")
    public UserResource unAssignUserInternship(@PathVariable(name = "userId") Long userId,
                                            @PathVariable(name = "internshipId") Long internshipId,
                                               @PathVariable(name = "requestId") Long requestId) {

        return convertToResource(userService.unassignUserInternship(userId, internshipId, requestId));
    }

    private User convertToEntity(SaveUserResource resource) { return mapper.map(resource, User.class); }

    private UserResource convertToResource(User entity) { return mapper.map(entity, UserResource.class); }

}
