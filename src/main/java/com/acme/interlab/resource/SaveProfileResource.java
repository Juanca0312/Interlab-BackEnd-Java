package com.acme.interlab.resource;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SaveProfileResource {

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String role;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String lastName;

    @NotNull
    @Size(max = 100)
    private String field;

    @NotNull
    @Size(max = 10)
    private String phone;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotNull
    @Lob
    private String description;

    @NotNull
    @Size(max = 100)
    private String country;

    @NotNull
    @Size(max = 100)
    private String city;

    @NotNull
    @Size(max = 100)
    private String university;

    @NotNull
    @Size(max = 100)
    private String degree;

    private int semester;
}
