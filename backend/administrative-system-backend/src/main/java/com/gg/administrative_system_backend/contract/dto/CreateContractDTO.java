package com.gg.administrative_system_backend.contract.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContractDTO {
    @NotBlank
    private String name;
}
