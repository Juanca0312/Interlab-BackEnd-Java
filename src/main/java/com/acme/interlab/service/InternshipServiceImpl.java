package com.acme.interlab.service;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Internship;
import com.acme.interlab.model.Profile;
import com.acme.interlab.model.Request;
import com.acme.interlab.repository.CompanyRepository;
import com.acme.interlab.repository.InternshipRepository;
import com.acme.interlab.repository.ProfileRepository;
import com.acme.interlab.repository.RequestRepository;
import com.acme.interlab.util.InternshipStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InternshipServiceImpl implements InternshipService {

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Internship> getAllInternships(Pageable pageable) {
        return internshipRepository.findAll(pageable);
    }

    @Override
    public Page<Internship> getAllActiveInternships(Pageable pageable) {
        Page<Internship> internshipsPage = internshipRepository.findAll(pageable);
        List<Internship> internships = internshipsPage.toList();
        List<Internship> activeInternships = new ArrayList<Internship>();

        for(Internship internship: internships){
            if(internship.getState().equalsIgnoreCase("active")){
                activeInternships.add(internship);
            }
        }

        int internshipsCount = activeInternships.size();

        return new PageImpl<>(activeInternships, pageable, internshipsCount);

    }

    @Override
    public Page<Internship> getAllActiveInternshipsByCompanyId(Long companyId, Pageable pageable) {
        Page<Internship> internshipsPage = internshipRepository.findByCompanyId(companyId, pageable);
        List<Internship> internships = internshipsPage.toList();
        List<Internship> companyActiveInternships = new ArrayList<Internship>();

        for(Internship internship: internships){
            if(internship.getState().equalsIgnoreCase("active")){
                companyActiveInternships.add(internship);
            }
        }

        int internshipsCount = companyActiveInternships.size();

        return new PageImpl<>(companyActiveInternships, pageable, internshipsCount);
    }

    @Override
    public Internship selectStudent(Long userId, Long internshipId) {
        Internship internship = internshipRepository.findById(internshipId).orElseThrow(() -> new ResourceNotFoundException(
                "profile not found with Id " ));
        List<Request> requests = internship.getRequests();


        for(Request request: requests){
            if(request.getUser().getId().equals(userId)){
                request.setState("active");
            }
            else {
                request.setState("rejected");
            }
            requestRepository.save(request);
        }

        internship.setState("ended");
        internshipRepository.save(internship);

        return internship;
    }

    @Override
    public Page<InternshipStudent> getAllEndedInternships(Long companyId, Pageable pageable) {
        Page<Internship> internshipsPage = internshipRepository.findByCompanyId(companyId, pageable);
        List<Internship> internships = internshipsPage.toList();
        List<InternshipStudent> endedInternships = new ArrayList<InternshipStudent>();

        for(Internship internship: internships){
            if(internship.getState().equalsIgnoreCase("ended")){
                InternshipStudent ended = new InternshipStudent();
                ended.setInternshipId(internship.getId());
                ended.setState(internship.getState());
                ended.setI_description(internship.getDescription());
                ended.setStartingDate(internship.getStartingDate());
                ended.setFinishingDate(internship.getFinishingDate());
                ended.setSalary(internship.getSalary());
                ended.setLocation(internship.getLocation());
                ended.setJobTitle(internship.getJobTitle());
                ended.setRequiredDocuments(internship.getRequiredDocuments());

                //student
                Request requestEnded = new Request();
                for(Request request: internship.getRequests()){
                    //agarramos el request "ended" que solo deberia haber 1, pues cuando finalizó, se debio cambiar el estado de los otros requests a rejectec
                    if(request.getState().equalsIgnoreCase("ended")){
                        requestEnded = request;
                    };
                }e
                Profile profileStudent = profileRepository.findByUserId(requestEnded.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException(
                        "profile not found with Id " ));
                ended.setFirstName(profileStudent.getFirstName());
                ended.setLastName(profileStudent.getLastName());
                ended.setField(profileStudent.getField());
                ended.setPhone(profileStudent.getPhone());
                ended.setEmail(profileStudent.getEmail());
                ended.setU_description(profileStudent.getDescription());
                ended.setCountry(profileStudent.getCountry());
                ended.setCity(profileStudent.getCity());
                ended.setUniversity(profileStudent.getUniversity());
                ended.setDegree(profileStudent.getDegree());
                ended.setSemester(profileStudent.getSemester());

                endedInternships.add(ended);
            }
        }

        int internshipsCount = endedInternships.size();

        return new PageImpl<>(endedInternships, pageable, internshipsCount);
    }

    @Override
    public Page<Internship> getAllInternshipsByCompanyId(Long companyId, Pageable pageable) {
        return internshipRepository.findByCompanyId(companyId, pageable);
    }

    @Override
    public Internship getInternshipByIdAndCompanyId(Long companyId, Long internshipId) {
        return internshipRepository.findByIdAndCompanyId(internshipId, companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Internship not found with Id " + internshipId +
                                " and Company Id " + companyId));
    }

    @Override
    public Internship createInternship(Long companyId, Internship internship) {
        return companyRepository.findById(companyId).map(company -> {
            internship.setCompany(company);
            return internshipRepository.save(internship);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Company", "Id", companyId));
    }

    @Override
    public Internship updateInternship(Long companyId, Long internshipId, Internship internshipDetails) {
        if(!companyRepository.existsById(companyId)) //existe?
            throw new ResourceNotFoundException("Company", "Id", companyId);

        return internshipRepository.findById(internshipId).map(internship -> {
            internship.setJobTitle(internshipDetails.getJobTitle());
            internship.setStartingDate(internshipDetails.getStartingDate());
            internship.setFinishingDate(internshipDetails.getFinishingDate());
            internship.setState(internshipDetails.getState());
            internship.setDescription(internshipDetails.getDescription());
            internship.setSalary(internshipDetails.getSalary());
            internship.setLocation(internshipDetails.getLocation());
            return internshipRepository.save(internship);
        }).orElseThrow(() -> new ResourceNotFoundException("Internship", "Id", internshipId));

    }

    @Override
    public ResponseEntity<?> deleteInternship(Long companyId, Long internshipId) {
        return internshipRepository.findByIdAndCompanyId(internshipId, companyId).map(internship -> {
            internshipRepository.delete(internship);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Internship not found with Id " + internshipId + " and CompanyId " + companyId));

    }

    @Override
    public Page<Internship> getAllInternshipsByUserId(Long userId, Pageable pageable) {
        Page<Request> requestsPage = requestRepository.findByUserId(userId, pageable);
        List<Request> requests = requestsPage.toList();
        List<Internship> internships = new ArrayList<Internship>();
        for (Request request: requests) {
            internships.add(request.getInternship());
        }
        int internshipsCount = internships.size();

        return new PageImpl<>(internships, pageable, internshipsCount);

    }
}
