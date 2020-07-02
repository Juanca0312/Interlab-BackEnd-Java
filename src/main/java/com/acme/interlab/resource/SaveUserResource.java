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
    @Size(max = 30)
    private String username;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String email;
}
