package com.acme.interlab.service;

import com.acme.interlab.model.Request;
import com.acme.interlab.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
    Page<User> getAllUsersByCompanyId(Long CompanyId, Pageable pageable);

    User getUserById(Long userId);
    User createUser(User user);
    User updateUser(Long userId, User userRequest);
    ResponseEntity<?> deleteUser(Long userId);

    User assignUserCompany(Long userId, Long companyId);
    User unAssignUserCompany(Long userId, Long companyId);

    User assignUserInternship(Long userId, Long internshipId);
    User unassignUserInternship(Long userId, Long internshipId, Long requestId);

}
