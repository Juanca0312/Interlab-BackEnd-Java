package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Company;
import com.acme.interlab.model.Internship;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(Long userId, User userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
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
    public User assignUserInternship(Long userId, Long internshipId) {
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "Id",internshipId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "Id", internshipId));

        Request request1 = new Request();
        request1.setState("Pending");
        request1.setUser(user);
        request1.setInternship(internship);
        user.getRequests().add(request1);
        internship.getRequests().add(request1);
        requestRepository.save(request1);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.acme.interlab.model.User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, buildGranted(user.getRole()) );
    }

    public List<GrantedAuthority> buildGranted(String role){
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(role));
        return auths;
    }
}
