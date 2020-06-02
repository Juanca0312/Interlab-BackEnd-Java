package com.acme.interlab.resource;

import com.acme.interlab.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProfileResource extends AuditModel {
    private Long id;
    private String role;
    private String firstName;
    private String lastName;
    private String field;
    private String phone;
    private String description;
    private String country;
    private String city;
    private String university;
    private String degree;
    private int semester;
}
