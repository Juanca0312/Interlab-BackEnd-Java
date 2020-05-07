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
@Table(name = "Companies")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

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

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(nullable = false)
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Workers",
            joinColumns = {@JoinColumn(name = "company_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @JsonIgnore
    List<User> users;

}
