package com.acme.interlab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "qualifications")
@Getter
@Setter

public class Qualification extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private double score;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String comment;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String author;

    public double getScore() {
        return score;
    }
    public String getComment() {return comment;}
    public String getAuthor() {
        return author;
    }

    public void setScore(double score) {
        this.score = score;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}
