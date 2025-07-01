package com.gg.administrative_system_backend.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCompanyDTO {
    @NotBlank
    private String name;
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
