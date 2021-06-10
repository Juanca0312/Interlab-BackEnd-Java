package com.acme.interlab.util;

import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestInternship {
    //internship
    private Long id;
    private String state;
    private String description;
    private String startingDate;
    private String finishingDate;
    private int salary;
    private String location;
    private String jobTitle;
    private String requiredDocuments;
    //request
    private String requestState;
    //company
    private String c_name;
    private String c_description;
    private String c_sector;
    private String c_email;
    private String c_phone;
    private String c_address;
    private String c_country;
    private String c_city;
}
