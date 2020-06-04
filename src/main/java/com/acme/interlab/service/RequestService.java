package com.acme.interlab.service;

import com.acme.interlab.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RequestService {

    Page<Request> getAllRequests(Pageable pageable);
    Page<Request> getAllRequestsByUserId(Long userId, Pageable pageable);

    Request getRequestByIdAUserIdAndInternshipId(Long userId, Long internshipId, Long requestId);
    Request createRequest(Long userId, Request request);
    Request updateRequest(Long internshipId, Long requestId, Request requestDetails);
    ResponseEntity<?> deleteRequest(Long userId, Long internshipId, Long requestId);
}
