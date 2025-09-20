package com.gg.administrative_system_backend.auth.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}
