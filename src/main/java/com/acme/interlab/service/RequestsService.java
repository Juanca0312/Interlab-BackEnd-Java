package com.acme.interlab.service;

import com.acme.interlab.model.Requests;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RequestsService {

    Page<Requests> getAllRequestsByUserId(Long userId, Pageable pageable);
    Requests getRequestsByIdAndUserId(Long userId, Long requestsId);
    Requests createRequests(Long userId, Requests requests);
    Requests updateRequests(Long userId, Long requestsId, Requests requestsDetails);
    ResponseEntity<?> deleteRequests(Long userId, Long requestsId);

}
