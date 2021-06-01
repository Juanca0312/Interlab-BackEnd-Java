package com.acme.interlab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String username;

    @NotNull
    @NotBlank
    @Size(max = 70)
    private String password;

    private String role;

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @OneToMany(mappedBy = "user")
    List<Request> requests;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_companies",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id")})
    @JsonIgnore
    List<Company> companies;
}
