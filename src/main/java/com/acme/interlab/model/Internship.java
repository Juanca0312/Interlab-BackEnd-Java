package com.acme.interlab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="internships")
@Data
public class Internship{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String state;

    @NotNull
    @Lob
    private String description;

    @NotNull
    private String startingDate;

    @NotNull
    private String finishingDate;

    @NotNull
    private int salary;

    @NotNull
    private String location;

    @NotNull
    private String jobTitle;

    private String requiredDocuments;

    @OneToOne(mappedBy = "internship")
    private Requirement requirement;

    //Relationships
    //Company muchos Internships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "companyId", nullable = false)
    @JsonIgnore
    private Company company;

    @OneToMany(mappedBy = "internship")
    List<Request> requests;


}
