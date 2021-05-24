package com.acme.interlab.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SaveRequestResource {

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String state;
}
