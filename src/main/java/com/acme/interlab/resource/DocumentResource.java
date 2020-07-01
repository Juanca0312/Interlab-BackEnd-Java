package com.acme.interlab.resource;

import com.acme.interlab.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DocumentResource{
    private Long id;
    private String name;
    private String description;


}
