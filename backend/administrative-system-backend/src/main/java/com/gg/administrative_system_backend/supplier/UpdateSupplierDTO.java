package com.gg.administrative_system_backend.supplier;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSupplierDTO {
    private String name;
    private Boolean status;
    private String rfc;
    @Email
    private String email;
    private String phoneNumber;
    private String services;
}
