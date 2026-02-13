package com.gg.administrative_system_backend.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSupplierDTO {
    private String name;
    private boolean status;
    private String rfc;
    @Email
    private String email;
    private String phoneNumber;
    private String services;
    private String state;
    private String municipality;
    private String locality;
    private String postalCode;
    private String street;
    private String interiorNumber;
    private String exteriorNumber;
}
