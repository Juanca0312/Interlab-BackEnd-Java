package com.acme.interlab.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveRequirementResource {

    @NotNull
    @NotBlank
    private String field;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String semester;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String degree;

    @NotNull
    @NotBlank
    @Lob
    private String description;
}
