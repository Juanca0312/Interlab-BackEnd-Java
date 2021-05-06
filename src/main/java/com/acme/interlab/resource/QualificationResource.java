package com.acme.interlab.resource;

import lombok.Data;

@Data
public class QualificationResource {
    private Long id;
    private double score;
    private String comment;
    private String author;
}
