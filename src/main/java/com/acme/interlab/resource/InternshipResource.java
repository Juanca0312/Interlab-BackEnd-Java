package com.acme.interlab.resource;

import com.acme.interlab.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InternshipResource extends AuditModel {
    private Long id;
    private String state;
    private String description;
    private double salary;
    private Date startingDate;
    private Date finishingDate;

    public InternshipResource() {
    }
}
