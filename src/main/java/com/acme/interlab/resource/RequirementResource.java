package com.acme.interlab.resource;

import lombok.Data;

@Data
public class RequirementResource {
    private Long id;
    private String field;
    private String semester;
    private String degree;
    private String description;
}
