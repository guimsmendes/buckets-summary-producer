package br.com.guimsmendes.entrypoint.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@RegisterForReflection
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotEmpty(message= "Username cannot be null or empty.")
    private String username;

    @NotEmpty(message= "Password cannot be null or empty.")
    private String password;
}
