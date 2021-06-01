package com.acme.interlab.resource;

import lombok.Data;

@Data
public class ProfileResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String field;
    private String phone;
    private String email;
    private String description;
    private String country;
    private String city;
    private String university;
    private String degree;
    private int semester;
}
