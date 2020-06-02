package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Requests;
import com.acme.interlab.repository.RequestsRepository;
import com.acme.interlab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RequestsServiceImpl implements  RequestsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestsRepository requestsRepository;


    @Override
    public Page<Requests> getAllRequestsByUserId(Long userId, Pageable pageable) {
        return requestsRepository.findByUserId(userId, pageable);
    }

    @Override
    public Requests getRequestsByIdAndUserId(Long userId, Long requestsId) {
        return requestsRepository.findByIdAndUserId(requestsId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Requests not found with Id " + requestsId +
                                " and PostId " + userId));
    }

    @Override
    public Requests createRequests(Long userId, Requests requests) {
        return userRepository.findById(userId).map(user -> {
            requests.setUser(user);
            return requestsRepository.save(requests);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "User", "Id", userId
        ));
    }

    @Override
    public Requests updateRequests(Long userId, Long requestsId, Requests requestsDetails) {
        if(!userRepository.existsById(userId))
            throw  new ResourceNotFoundException("User", "Id", userId);

        return requestsRepository.findById(requestsId).map(requests -> {
            requests.setState(requestsDetails.getState());
            return requestsRepository.save(requests);
        }).orElseThrow(() -> new ResourceNotFoundException("Requests", "Id", requestsId));
    }

    @Override
    public ResponseEntity<?> deleteRequests(Long userId, Long requestsId) {
        return requestsRepository.findByIdAndUserId(requestsId, userId).map(requests -> {
            requestsRepository.delete(requests);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Requests not found with Id " + requestsId + " and UserId " + userId
        ));
    }
}
