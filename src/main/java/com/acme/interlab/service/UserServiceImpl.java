package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Company;
import com.acme.interlab.model.Request;
import com.acme.interlab.model.User;
import com.acme.interlab.repository.CompanyRepository;
import com.acme.interlab.repository.InternshipRepository;
import com.acme.interlab.repository.RequestRepository;
import com.acme.interlab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
      return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> getAllUsersByCompanyId(Long companyId, Pageable pageable) {
        return companyRepository.findById(companyId).map(company -> {
            List<User> users = company.getUsers();
            int usersCount = users.size();
            return new PageImpl<>(users, pageable, usersCount);
        })
         .orElseThrow(() -> new ResourceNotFoundException("Company", "Id", companyId));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public User assignUserCompany(Long userId, Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "Id", companyId));
        return userRepository.findById(userId).map(user -> {
            if(!user.getCompanies().contains(company)) {
                user.getCompanies().add(company);
                return userRepository.save(user);
            }
            return user;
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

    }

    @Override
    public User unAssignUserCompany(Long userId, Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "Id", companyId));
        return userRepository.findById(userId).map(user -> {
            user.getCompanies().remove(company);
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }


    @Override
    public User unassignUserInternship(Long userId, Long internshipId, Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "Id",requestId));
        internshipRepository.findById(internshipId).map(internship -> {
            if (!internship.getRequests().contains(request)) {
                internship.getRequests().remove(request);
                internshipRepository.save(internship);
            }
            return internship;
        }).orElseThrow(() -> new ResourceNotFoundException("UserId","Id", userId));
        return userRepository.findById(userId).map(user -> {
            if(!user.getRequests().contains(request)){
                user.getRequests().remove(request);
                return userRepository.save(user);
            }
            return user;
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }


    @Override
    public User assignUserInternship(Long userId, Long internshipId, Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "Id",requestId));
        internshipRepository.findById(internshipId).map(internship -> {
            if(!internship.getRequests().contains(request)) {
                internship.getRequests().add(request);
                internshipRepository.save(internship);
            }
            return internship;
        }).orElseThrow(() -> new ResourceNotFoundException("Internship", "Id", internshipId));

        return userRepository.findById(userId).map(user -> {
            if(!user.getRequests().contains(request)){
                user.getRequests().add(request);
                return userRepository.save(user);
            }
            return user;
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }


}
