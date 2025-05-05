package com.gg.administrative_system_backend.contract;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContractDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
}
