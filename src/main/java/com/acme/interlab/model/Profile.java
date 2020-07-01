package com.acme.interlab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "profiles")
@Getter
@Setter
public class Profile{
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
    private int semester;

    //Relationships
    //User One To One
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
