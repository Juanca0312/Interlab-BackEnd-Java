package com.acme.interlab.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveUserResource {
    @NotNull
    @NotBlank
    @Size(max = 20)
    private String Username;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String Password;
}
