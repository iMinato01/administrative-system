package com.gg.administrative_system_backend.contract;

import jakarta.validation.constraints.NotBlank;

public class CreateContractDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
