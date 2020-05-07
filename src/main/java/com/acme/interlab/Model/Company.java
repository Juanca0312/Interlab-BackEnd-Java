package com.acme.interlab.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import sun.util.resources.Bundles;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table (name = "Companies")
@Getter
@Setter
public class Company extends  AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    String Name;

    @NotNull
    @NotBlank
    @Size(max = 100)
    String Description;

    @NotNull
    @NotBlank
    @Size (max = 30)
    String Sector;

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    String Mail;

    @NotNull
    @NotBlank
    @Size(max = 15)
    @Column(unique = true)
    String Phone_number;

    @NotNull
    @NotBlank
    @Size(max = 20)
    String Address;

    @NotNull
    @NotBlank
    @Size(max = 20)
    String Country;

    @NotNull
    @NotBlank
    @Size(max = 20)
    String City;

    //Relation Many to Many with Users (Table = Workers)
    /*@ManyToMany(fetch = FetchType.LAZY,
    cascade =  {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Workers",
    joinColumns =  {@JoinColumn(name = "Worker_id") },
    inverseJoinColumns = {@JoinColumn(name = "Compamy_id")})
    @JsonIgnore
    List<Company> companies;
    */
}
