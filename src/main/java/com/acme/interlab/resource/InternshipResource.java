package com.acme.interlab.resource;

import com.acme.interlab.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class InternshipResource{
    private Long id;
    private String state;
    private String description;
    private String startingDate;
    private String finishingDate;
    private int salary;
    private String location;
    private String jobTitle;
    private String requiredDocuments;

}
