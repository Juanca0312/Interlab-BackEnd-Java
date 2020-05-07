package com.acme.interlab.controller;

import com.acme.interlab.model.Internship;
import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/api")
public class InternshipsController {
    @Autowired
    InternshipRepository internshipRepository;

    @GetMapping("/internships")
    public List<Internship> getAllInternships(){ return internshipRepository.findAll(); }

    public Internship createInternship(@Valid @RequestBody Internship internship){
        return internshipRepository.save(internship);
    }

    @GetMapping("internships/{id}")
    public Internship getInternshipById(@PathVariable(value =  "id") Long internshipId){
        return internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", internshipId));
    }

    @PutMapping("/internships/{id}")
    public Internship updateInternship(@PathVariable(value =  "id")  Long internshipId, @Valid @RequestBody Internship internshipDetails){
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", internshipId));
        //internship.setTitle(internshipDetails.getTitle());
        //internship.setContent(internshipDetails.getContent());
        return internshipRepository.save(internship);
    }

    @DeleteMapping("/internships/{id}")
    public ResponseEntity<?> deleteInternship(@PathVariable(value ="id") Long internshipId){
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", internshipId));
        internshipRepository.delete(internship);
        return ResponseEntity.ok().build();
    }
}
