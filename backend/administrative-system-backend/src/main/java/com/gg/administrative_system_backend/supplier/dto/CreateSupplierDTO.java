package com.gg.administrative_system_backend.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSupplierDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String rfc;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;
    private String state;
    private String municipality;
    private String locality;
    private String postalCode;
    private String street;
    private String interiorNumber;
    private String exteriorNumber;
    @NotBlank
    private String services;
}
