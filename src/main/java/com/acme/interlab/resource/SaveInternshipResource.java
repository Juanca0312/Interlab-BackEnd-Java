package com.acme.interlab.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SaveInternshipResource {

    @NotNull
    private String state;

    @NotNull
    @Lob
    private String description;

    @NotNull
    private String startingDate;

    @NotNull
    private String finishingDate;

    @NotNull
    private int salary;

    @NotNull
    private String location;

    @NotNull
    private String jobTitle;

    private String requiredDocuments;
}
