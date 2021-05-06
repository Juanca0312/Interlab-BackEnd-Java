package com.acme.interlab.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SaveQualificationResource {

    @NotNull
    @NotBlank
    private double score;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String comment;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String author;
}
