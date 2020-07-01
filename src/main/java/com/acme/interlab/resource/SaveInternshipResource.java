package com.acme.interlab.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class SaveInternshipResource {

    @NotBlank
    private String state;

    @NotBlank
    private String description;


    @NotBlank
    private String startingDate;

    @NotBlank
    private String finishingDate;

    @NotNull
    private int salary;

    @NotNull
    private String location;

    @NotNull
    private String jobTile;

    @NotNull
    private String requiredDocuments;
}
