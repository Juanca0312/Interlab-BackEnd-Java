package com.acme.interlab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "companies")
    @JsonIgnore
    private List<User> users;
}
