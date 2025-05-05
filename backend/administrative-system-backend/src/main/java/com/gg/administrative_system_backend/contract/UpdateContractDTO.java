package com.gg.administrative_system_backend.contract;

import jakarta.validation.constraints.DecimalMin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateContractDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotNull(message = "El status no puede estar vacío")
    @BooleanFlag
    private Boolean status;
    @NotNull(message = "El monto no puede estar vacío")
    @DecimalMin(value = "0.0", message = "El valor debe ser número positivo")
    private Float totalExpenses;
}