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


@Entity
@Table(name="internships")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Internship implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String state;

    @NotBlank
    private String description;

    private Date publicationDate;

    @NotBlank
    private Date startingDate;

    @NotBlank
    private Date finishingDate;

    private double salary;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(nullable = false)
    private Date updatedAt;

    //Relationships
    //Company muchos Internships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "companyId", nullable = false)
    @JsonIgnore
    private Company company;

    @OneToMany(mappedBy = "internship")
    List<Request> requests;


}
