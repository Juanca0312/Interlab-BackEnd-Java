package com.acme.interlab.resource;

import com.acme.interlab.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResource extends AuditModel {
    private Long id;
    private String field;
    private String semester;
    private String degree;
    private String description;
}
