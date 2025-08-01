package com.gg.administrative_system_backend.contract.dto;

import com.gg.administrative_system_backend.shared.message.ValidationMessage;
import jakarta.validation.constraints.DecimalMin;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateContractDTO {
    private String name;
    private Boolean status;
    @DecimalMin(value = "0.0", message = ValidationMessage.AMMOUNT_POSITIVE)
    private BigDecimal totalExpenses;
}