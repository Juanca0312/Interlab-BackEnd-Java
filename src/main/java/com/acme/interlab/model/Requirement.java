package com.acme.interlab.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "requirements")
@Data
public class Requirement{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String field;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String semester;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String degree;

    @NotNull
    @NotBlank
    @Lob
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "intership_id", referencedColumnName = "id")
    private Internship internship;
}
