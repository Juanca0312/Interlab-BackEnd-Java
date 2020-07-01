package com.acme.interlab.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
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
