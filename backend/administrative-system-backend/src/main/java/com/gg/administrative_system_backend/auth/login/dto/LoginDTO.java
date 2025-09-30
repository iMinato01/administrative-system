package com.gg.administrative_system_backend.auth.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data Transfer Object (DTO) that represents the login request. <br>
 * Contains the required fields for user authentication.
 */
@AllArgsConstructor
@Getter
public class LoginDTO {
    /**
     * The unique identifier (RFC) of the user. <br>
     * Must not be blank.
     */
    @NotBlank
    private String rfc;
    /**
     * The password of the user. <br>
     * Must not be blank.
     */
    @NotBlank
    private String password;
}
