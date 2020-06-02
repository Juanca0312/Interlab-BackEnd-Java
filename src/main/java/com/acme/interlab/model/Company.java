package com.acme.interlab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "companies")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 20)
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
    @Size(max = 30)
    private String country;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String city;


    //Relationships:

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "companies")
    @JsonIgnore
    private List<User> users;


}
