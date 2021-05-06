package com.acme.interlab.resource;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SaveCompanyResource {

    @NotNull
    @NotBlank
    @Size(max = 40)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Lob
    private String description;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String sector;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 15)
    private String phone;

    @NotNull
    @NotBlank
    @Size(max = 70)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String country;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String city;
}
