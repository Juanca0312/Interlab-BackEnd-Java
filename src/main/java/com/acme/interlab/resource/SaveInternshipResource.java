package com.acme.interlab.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class SaveInternshipResource {

    @NotBlank
    private String state;

    @NotBlank
    private String description;

    private double salary;

    @NotBlank
    private Date startingDate;

    @NotBlank
    private Date finishingDate;
}
