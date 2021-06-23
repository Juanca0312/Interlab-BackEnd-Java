package com.acme.interlab.util;
import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class InternshipStudent {

    //internship
    private Long internshipId;
    private String state;
    private String i_description;
    private String startingDate;
    private String finishingDate;
    private int salary;
    private String location;
    private String jobTitle;
    private String requiredDocuments;

    //student-profile
    private String firstName;
    private String lastName;
    private String field;
    private String phone;
    private String email;
    private String u_description;
    private String country;
    private String city;
    private String university;
    private String degree;
    private int semester;


}
