package com.acme.interlab.resource;

import com.acme.interlab.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResource extends AuditModel {
    private Long Id;
    private String Username;
    private String Password;
}
