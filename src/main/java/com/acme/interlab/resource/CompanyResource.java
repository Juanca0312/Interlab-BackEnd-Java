package com.acme.interlab.resource;

import com.acme.interlab.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResource{
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