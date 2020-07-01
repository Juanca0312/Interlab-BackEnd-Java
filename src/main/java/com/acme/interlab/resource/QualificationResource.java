package com.acme.interlab.resource;

import com.acme.interlab.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QualificationResource {
    private Long id;
    private double score;
    private String comment;
    private String author;
}
