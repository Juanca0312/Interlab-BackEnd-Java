package com.acme.interlab.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveProfileResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String field;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String semester;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String degree;

    @NotNull
    @Lob
    private String description;
}
