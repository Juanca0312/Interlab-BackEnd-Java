package com.acme.interlab.resource;

import com.acme.interlab.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestResource extends AuditModel {

    private long id;
    private String state;

}
