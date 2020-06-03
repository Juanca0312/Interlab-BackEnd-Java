package com.acme.interlab.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
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
