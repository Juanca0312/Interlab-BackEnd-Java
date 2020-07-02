package com.acme.interlab.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
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
