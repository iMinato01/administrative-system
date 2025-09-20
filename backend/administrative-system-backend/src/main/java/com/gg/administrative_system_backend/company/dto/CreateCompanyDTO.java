package com.gg.administrative_system_backend.company.dto;

import com.gg.administrative_system_backend.shared.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCompanyDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotNull
    private Role role;
    @NotBlank
    private String rfc;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String state;
    @NotBlank
    private String municipality;
    @NotBlank
    private String locality;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String street;
    private String interiorNumber;
    private String exteriorNumber;
}
