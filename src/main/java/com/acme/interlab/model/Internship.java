package com.acme.interlab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="internships")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Getter
@Setter
public class Internship implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String state;

    @NotBlank
    private String description;

    private double salary;

    @NotBlank
    private Date startingDate;

    @NotBlank
    private Date finishingDate;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(nullable = false)
    private Date updatedAt;


    public String getState() {
        return state;
    }
    public String getDescription() {
        return description;
    }
    public double getSalary() {
        return salary;
    }
    public Date getStartingDate() {
        return startingDate;
    }
    public Date getFinishingDate() {
        return finishingDate;
    }

    public void setState(String state) {
        this.state = state;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }
    public void setFinishingDate(Date finishingDate) {
        this.finishingDate = finishingDate;
    }
}
