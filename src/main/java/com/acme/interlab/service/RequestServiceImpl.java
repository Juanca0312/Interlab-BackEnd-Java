package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Request;
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
public class RequestServiceImpl implements RequestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private InternshipRepository internshipRepository;


    @Override
    public Page<Request> getAllRequests(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

    @Override
    public Page<Request> getAllRequestsByUserId(Long userId, Pageable pageable) {
        return userRepository.findById(userId).map(user -> {
            List<Request> request = user.getRequests();
            int requestCount = request.size();
            return new PageImpl<>(request, pageable, requestCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Request getRequestByIdAUserIdAndInternshipId(Long userId, Long internshipId, Long requestId) {
        return requestRepository.findByIdAndUserIdAndInternshipId(requestId, userId, internshipId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Request not found with Id " + requestId +
                                " UserId " + userId + "and InternshipId" + internshipId));
    }


    @Override
    public Request createRequest(Long userId, Request request) {
        return userRepository.findById(userId).map(user -> {
            request.setUser(user);
            return requestRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }


    @Override
    public Request updateRequest(Long internshipId, Long requestId, Request requestDetails) {
        if(!internshipRepository.existsById(internshipId))
            throw  new ResourceNotFoundException("Internship", "Id",internshipId);

        return requestRepository.findById(requestId).map(request -> {
            request.setState(requestDetails.getState());
            return requestRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("Request", "Id", requestId));
    }


    @Override
    public ResponseEntity<?> deleteRequest(Long userId, Long internshipId, Long requestId) {
        return requestRepository.findByIdAndUserIdAndInternshipId(requestId, userId, internshipId).map(request -> {
            requestRepository.delete(request);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Request not found with Id " + requestId + " UserId " + userId + "and InternshipId"
        ));
    }

}
