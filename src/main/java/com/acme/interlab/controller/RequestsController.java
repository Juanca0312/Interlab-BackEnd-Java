package com.acme.interlab.controller;


import com.acme.interlab.model.Requests;
import com.acme.interlab.resource.RequestsResource;
import com.acme.interlab.resource.SaveRequestsResource;
import com.acme.interlab.service.RequestsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RequestsController {

@Autowired
    private ModelMapper mapper;

@Autowired
    private RequestsService requestsService;


    @GetMapping("/users/{userId}/requests")
    public Page<RequestsResource> getAllRequestsByUserId(
            @PathVariable(name = "userId") Long userId,
            Pageable pageable)
    {
        Page<Requests> requestsPage = requestsService.getAllRequestsByUserId(userId, pageable);
        List<RequestsResource> resources = requestsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/users/{userId}/requests/{requestsId}")
    public RequestsResource getRequestsByIdAndUserId(@PathVariable(name = "userId") Long userId,
                                                     @PathVariable(name = "requestsId") Long requestsId){
        return convertToResource(requestsService.getRequestsByIdAndUserId(userId, requestsId));
    }



    @PostMapping("/users/{userId}/requests")
    public RequestsResource createRequests(@PathVariable(name = "userId") Long userId,
                                           @Valid @RequestBody SaveRequestsResource resource){
        return convertToResource(requestsService.createRequests(userId, convertToEntity(resource)));
    }



    @PutMapping("/users/{userId}/requests/{requestsId}")
    public RequestsResource updateDocument(@PathVariable(name = "userId") Long userId,
                                           @PathVariable(name = "requestsId") Long requestsId,
                                           @Valid @RequestBody SaveRequestsResource resource){
        return convertToResource(requestsService.updateRequests(userId, requestsId, convertToEntity(resource)));
    }


    @DeleteMapping("/users/{userId}/requests/{requestsId}")
    public ResponseEntity<?> deleteRequests(@PathVariable(name = "userId") Long userId,
                                            @PathVariable(name="requestsId") Long requestsId){
        return  requestsService.deleteRequests(userId, requestsId);
    }

    private Requests convertToEntity(SaveRequestsResource resource) {
        return mapper.map(resource, Requests.class);
    }

    private RequestsResource convertToResource(Requests entity) {
        return mapper.map(entity, RequestsResource.class);
    }


}
