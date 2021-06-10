package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Company;
import com.acme.interlab.model.Internship;
import com.acme.interlab.model.Request;
import com.acme.interlab.model.User;
import com.acme.interlab.repository.InternshipRepository;
import com.acme.interlab.repository.RequestRepository;
import com.acme.interlab.repository.UserRepository;
import com.acme.interlab.util.RequestInternship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Page<Request> getAllRequestsByInternshipId(Long internshipId, Pageable pageable) {
        return internshipRepository.findById(internshipId).map(internship -> {
            List<Request> request = internship.getRequests();
            int requestCount = request.size();
            return new PageImpl<>(request, pageable, requestCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "Id", internshipId));
    }

    //Para Home
    @Override
    public Page<RequestInternship> getAllRequestInternshipByUserId(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        List<Request> requests = user.getRequests();
        List<RequestInternship> response = new ArrayList<>();
        for(Request request : requests){
            Internship internship = request.getInternship();
            Company company = internship.getCompany();
            RequestInternship requestInternship = new RequestInternship();

            //request
            requestInternship.setRequestState(request.getState());
            //internship
            requestInternship.setId(internship.getId());
            requestInternship.setState(internship.getState());
            requestInternship.setDescription(internship.getDescription());
            requestInternship.setStartingDate(internship.getStartingDate());
            requestInternship.setFinishingDate(internship.getFinishingDate());
            requestInternship.setSalary(internship.getSalary());
            requestInternship.setLocation(internship.getLocation());
            requestInternship.setJobTitle(internship.getJobTitle());
            requestInternship.setRequiredDocuments(internship.getRequiredDocuments());
            //company
            requestInternship.setC_name(company.getName());
            requestInternship.setC_description(company.getDescription());
            requestInternship.setC_sector(company.getSector());
            requestInternship.setC_email(company.getEmail());
            requestInternship.setC_phone(company.getPhone());
            requestInternship.setC_address(company.getAddress());
            requestInternship.setC_country(company.getCountry());
            requestInternship.setC_city(company.getCity());

            response.add(requestInternship);

        }

        return new PageImpl<>(response, pageable, response.size());

    }

    @Override
    public Page<RequestInternship> getAllEndedRequestInternshipByUserId(Long userId, Pageable pageable) {
        Page<Request> requests = requestRepository.findByUserIdAndState(userId, "ended", pageable );
        List<RequestInternship> response = new ArrayList<>();
        for(Request request : requests){
            Internship internship = request.getInternship();
            Company company = internship.getCompany();
            RequestInternship requestInternship = new RequestInternship();

            //request
            requestInternship.setRequestState(request.getState());
            //internship
            requestInternship.setId(internship.getId());
            requestInternship.setState(internship.getState());
            requestInternship.setDescription(internship.getDescription());
            requestInternship.setStartingDate(internship.getStartingDate());
            requestInternship.setFinishingDate(internship.getFinishingDate());
            requestInternship.setSalary(internship.getSalary());
            requestInternship.setLocation(internship.getLocation());
            requestInternship.setJobTitle(internship.getJobTitle());
            requestInternship.setRequiredDocuments(internship.getRequiredDocuments());
            //company
            requestInternship.setC_name(company.getName());
            requestInternship.setC_description(company.getDescription());
            requestInternship.setC_sector(company.getSector());
            requestInternship.setC_email(company.getEmail());
            requestInternship.setC_phone(company.getPhone());
            requestInternship.setC_address(company.getAddress());
            requestInternship.setC_country(company.getCountry());
            requestInternship.setC_city(company.getCity());

            response.add(requestInternship);

        }

        return new PageImpl<>(response, pageable, response.size());

    }

    @Override
    public Request getRequestByIdAUserIdAndInternshipId(Long userId, Long internshipId, Long requestId) {
        return requestRepository.findByIdAndUserIdAndInternshipId(requestId, userId, internshipId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Request not found with Id " + requestId +
                                " UserId " + userId + "and InternshipId" + internshipId));
    }


    @Override
    public Request createRequest(Long userId, Long internshipId, Request request) {
        return userRepository.findById(userId).map(user -> {
            request.setUser(user);
            Internship internship = internshipRepository.findById(internshipId).orElseThrow(() -> new ResourceNotFoundException("Internship", "Id", internshipId));
            request.setInternship(internship);
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
