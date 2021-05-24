package com.acme.interlab.resource;

import lombok.Data;

@Data
public class CompanyResource {
    private Long id;
    private String name;
    private String description;
    private String sector;
    private String email;
    private String phone;
    private String address;
    private String country;
    private String city;
}