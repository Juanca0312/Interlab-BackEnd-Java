package com.acme.interlab.resource;

import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Data
public class SaveStudentResource {
    @NotNull
    private String university;
}
