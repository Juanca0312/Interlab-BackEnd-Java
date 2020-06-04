package com.acme.interlab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "requirements")
@Getter
@Setter
public class Requirement extends AuditModel{
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
    @Size(max = 150)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "intership_id", referencedColumnName = "id")
    private Internship internship;

    public String getField() {
        return field;
    }
    public String getSemester() {return semester;}
    public String getDegree() {
        return degree;
    }

    public String getDescription() {
        return degree;
    }

    public void setField(String field) {
        this.field = field;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
