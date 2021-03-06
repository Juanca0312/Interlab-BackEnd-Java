package com.acme.interlab.controller;


import com.acme.interlab.model.Request;
import com.acme.interlab.resource.RequestResource;
import com.acme.interlab.resource.SaveRequestResource;
import com.acme.interlab.service.RequestService;
import com.acme.interlab.util.RequestInternship;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "requests", description = "Requests API")
@RestController("Request")
@CrossOrigin
@RequestMapping("/api")
public class RequestController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RequestService requestService;


    @GetMapping("/requests")
    public Page<RequestResource> getAllRequests(Pageable pageable) {
        List<RequestResource> requests = requestService.getAllRequests(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int requestCount = requests.size();
        return new PageImpl<>(requests, pageable, requestCount);
    }

    //Para pantalla de HOME
    @GetMapping("/users/{userId}/requests")
    public Page<RequestInternship> getAllRequestsByUserId(
            @PathVariable(name = "userId") Long userId,
            Pageable pageable) {
        return requestService.getAllRequestInternshipByUserId(userId, pageable);
    }

    //Para pantalla History
    @GetMapping("/users/{userId}/endedRequest")
    public Page<RequestInternship> getAllEndedRequestsByUserId(
            @PathVariable(name = "userId") Long userId,
            Pageable pageable) {
        return requestService.getAllEndedRequestInternshipByUserId(userId, pageable);
    }

    @GetMapping("/internship/{internshipId}/requests")
    public Page<RequestResource> getAllRequestsByInternshipId(
            @PathVariable(name = "internshipId") Long internshipId,
            Pageable pageable) {
        Page<Request> requestPage = requestService.getAllRequestsByInternshipId(internshipId, pageable);
        List<RequestResource> resources = requestPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/users/{userId}/internships/{internshipId}/requests/{requestId}")
    public RequestResource getRequestsByIdAndUserId(@PathVariable(name = "userId") Long userId,
                                                    @PathVariable(name = "internshipId") Long internshipId,
                                                    @PathVariable(name = "requestId") Long requestsId) {
        return convertToResource(requestService.getRequestByIdAUserIdAndInternshipId(userId, internshipId, requestsId));
    }

    @PostMapping("/users/{userId}/internships/{internshipId}/request")
    public RequestResource createRequests(@PathVariable(name = "userId") Long userId,
                                          @PathVariable(name = "internshipId") Long internshipId,
                                          @Valid @RequestBody SaveRequestResource resource) {
        return convertToResource(requestService.createRequest(userId, internshipId, convertToEntity(resource)));
    }


    @PutMapping("/internships/{internshipId}/requests/{requestId}")
    public RequestResource updateRequest(@PathVariable(name = "internshipId") Long internshipId,
                                         @PathVariable(name = "requestId") Long requestsId,
                                         @Valid @RequestBody SaveRequestResource resource) {
        return convertToResource(requestService.updateRequest(internshipId, requestsId, convertToEntity(resource)));
    }


    @DeleteMapping("/users/{userId}/internships/{internshipId}/requests/{requestsId}")
    public ResponseEntity<?> deleteRequests(@PathVariable(name = "userId") Long userId,
                                            @PathVariable(name = "internshipId") Long internshipId,
                                            @PathVariable(name = "requestsId") Long requestsId) {
        return requestService.deleteRequest(userId, internshipId, requestsId);
    }

    private Request convertToEntity(SaveRequestResource resource) {
        return mapper.map(resource, Request.class);
    }

    private RequestResource convertToResource(Request entity) {
        return mapper.map(entity, RequestResource.class);
    }


}
