package com.acme.interlab.controller;

import com.acme.interlab.model.User;
import com.acme.interlab.resource.SaveUserResource;
import com.acme.interlab.resource.UserResource;
import com.acme.interlab.service.UserService;
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

@RestController
@RequestMapping("/api")

public class UsersController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

@GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable){
    List<UserResource> users = userService.getAllUsers(pageable)
            .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
    int usersCount = users.size();
    return new PageImpl<>(users, pageable, usersCount);
}

@GetMapping("/companies/{companyId}/users")
public Page<UserResource> getAllUsersByCompanyId(@PathVariable(name = "companyId") Long companyId, Pageable pageable){
    List<UserResource> users = userService.getAllUsersByCompanyId(companyId, pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());
    int userCount = users.size();
    return new PageImpl<>(users, pageable, userCount);
}


@GetMapping("/users/{id}")
public UserResource getUserById(@PathVariable(name = "id") Long userId){
    return convertToResource(userService.getUserById(userId));
}

@PostMapping("/users")
public UserResource createUser(@Valid @RequestBody SaveUserResource resource){
    return convertToResource(userService.createUser(convertToEntity(resource)));
}

@PutMapping("/users/{id}")
    public UserResource updateTag(@PathVariable(name = "id") Long userId, @Valid @RequestBody SaveUserResource resource) {
        return convertToResource(userService.updateUser(userId, convertToEntity(resource)));
    }

@DeleteMapping("/userss/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long userId)
{
        return userService.deleteUser(userId);
}




private User convertToEntity(SaveUserResource resource)
{
    return mapper.map(resource, User.class);
}

private UserResource convertToResource(User entity)
{
    return mapper.map(entity,UserResource.class);
}


}
