package com.acme.interlab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "profiles")
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private User user;
}
