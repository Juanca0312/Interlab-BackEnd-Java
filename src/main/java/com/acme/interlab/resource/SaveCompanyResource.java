package com.acme.interlab.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveCompanyResource {

    @NotNull
    @NotBlank
    @Size(max = 30)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String description;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String sector;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 15)
    private String phone;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String country;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String city;
}
