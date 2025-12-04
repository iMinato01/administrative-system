package com.gg.administrative_system_backend.contract.dto;

import jakarta.validation.constraints.DecimalMin;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateContractDTO {
    private String name;
    private Boolean status;
    @DecimalMin(value = "0.0")
    private BigDecimal totalExpenses;
}