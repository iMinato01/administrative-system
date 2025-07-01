package com.gg.administrative_system_backend.supplier.dto;

import com.gg.administrative_system_backend.shared.ValidationMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSupplierDTO {
    @NotBlank(message = ValidationMessage.NAME_REQUIRED)
    private String name;
    @NotBlank(message = ValidationMessage.RFC_REQUIRED)
    private String rfc; //Crear validación @
    @NotBlank(message = ValidationMessage.EMAIL_REQUIRED)
    @Email
    private String email;
    @NotBlank(message = ValidationMessage.PHONE_REQUIRED)
    private String phoneNumber; //Crear validación @
    @NotBlank(message = ValidationMessage.SERVICE_REQUIRED)
    private String services;
}
