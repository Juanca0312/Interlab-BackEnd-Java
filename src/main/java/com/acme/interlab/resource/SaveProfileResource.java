package com.acme.interlab.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NotBlank
    @Size(max = 100)
    private String field;

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String phone;

    @NotNull
    @Lob
    private String description;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String country;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String city;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String university;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String degree;

    @NotNull
    @NotBlank
    private int semester;
}
