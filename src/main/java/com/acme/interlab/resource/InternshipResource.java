package com.acme.interlab.resource;

import lombok.Data;

@Data
public class InternshipResource {
    private Long id;
    private String state;
    private String description;
    private String startingDate;
    private String finishingDate;
    private int salary;
    private String location;
    private String jobTitle;
    private String requiredDocuments;
}
