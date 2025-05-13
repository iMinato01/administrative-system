package com.gg.administrative_system_backend.contract;

import com.gg.administrative_system_backend.message.ValidationMessage;
import jakarta.validation.constraints.DecimalMin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateContractDTO {
    @NotBlank(message = ValidationMessage.NAME_REQUIRED)
    private String name;
    @NotNull(message = ValidationMessage.STATUS_REQUIRED)
    @BooleanFlag
    private Boolean status;
    @NotNull(message = ValidationMessage.AMMOUNT_REQUIRED)
    @DecimalMin(value = "0.0", message = ValidationMessage.AMMOUNT_POSITIVE)
    private BigDecimal totalExpenses;
}