package com.acme.interlab.resource;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SaveDocumentResource {

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Lob
    private String description;
}
