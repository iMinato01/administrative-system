package com.gg.administrative_system_backend.supplier;

import com.gg.administrative_system_backend.message.ValidationMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSupplierDTO {
    @NotBlank(message = ValidationMessage.NAME_REQUIRED)
    private String name;
    @NotNull(message = ValidationMessage.STATUS_REQUIRED)
    private Boolean status;
    @NotBlank(message = ValidationMessage.RFC_REQUIRED)
    private String rfc;
    @NotBlank(message = ValidationMessage.EMAIL_REQUIRED)
    @Email
    private String email;
    @NotBlank(message = ValidationMessage.PHONE_REQUIRED)
    private String phoneNumber;
    @NotBlank(message = ValidationMessage.SERVICE_REQUIRED)
    private String services;
}
