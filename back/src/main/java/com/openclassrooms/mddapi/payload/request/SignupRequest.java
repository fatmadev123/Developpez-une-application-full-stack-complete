package com.openclassrooms.mddapi.payload.request;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class SignupRequest {
    @NotNull
    @Size(max = 50)
    @Email
    private String email;

    @NotNull
    @Size(min = 1, max = 50)
    private String userName;

    @NotNull
    @Size(min = 8, max = 40)
    private String password;
}
