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
    @Size(max = 20)
    @Column(unique = true)
    private String Name;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String Description;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String Sector;


    @NotNull
    @NotBlank
    @Size(max = 20)
    private String Mail;


    @NotNull
    @NotBlank
    @Size(max = 20)
    private String Phone_number;


    @NotNull
    @NotBlank
    @Size(max = 20)
    private String Address;


    @NotNull
    @NotBlank
    @Size(max = 20)
    private String Country;


    @NotNull
    @NotBlank
    @Size(max = 20)
    private String City;
}
