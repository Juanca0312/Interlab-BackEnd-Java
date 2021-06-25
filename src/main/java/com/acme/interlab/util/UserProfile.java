package com.acme.interlab.util;

import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserProfile {
    private Long userId;
    private String username;
    private String password;
    private String role;

    private Long profileId;
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
