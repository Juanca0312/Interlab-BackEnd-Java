package com.acme.interlab.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(max = 150)
    private String description;
}
