package com.acme.interlab.service;

import com.acme.interlab.model.Request;
import com.acme.interlab.util.RequestInternship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RequestService {
    Page<Request> getAllRequests(Pageable pageable);
    Page<Request> getAllRequestsByUserId(Long userId, Pageable pageable);
    Page<Request> getAllRequestsByInternshipId(Long internshipId, Pageable pageable);
    Request getRequestByIdAUserIdAndInternshipId(Long userId, Long internshipId, Long requestId);
    Request createRequest(Long userId,Long internshipId, Request request);
    Request updateRequest(Long internshipId, Long requestId, Request requestDetails);
    ResponseEntity<?> deleteRequest(Long userId, Long internshipId, Long requestId);

    //Para company: Necesito getRequest with user
    //Para user: Necesito getRequest with internship
    Page<RequestInternship> getAllRequestInternshipByUserId(Long userId, Pageable pageable);
    Page<RequestInternship> getAllEndedRequestInternshipByUserId(Long userId, Pageable pageable);

}
