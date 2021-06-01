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
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String lastName;

    @Size(max = 100)
    private String field;

    @Size(max = 10)
    private String phone;

    @Size(max = 50)
    private String email;

    @Lob
    private String description;

    @Size(max = 100)
    private String country;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String university;

    @Size(max = 100)
    private String degree;

    private int semester;
}
