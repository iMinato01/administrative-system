package com.gg.administrative_system_backend.pettycash.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateExpenseDTO {
    @NotNull
    private LocalDate date;
    @NotNull
    private Long supplierId;
    @NotNull
    private String description;
    @NotNull
    private Long contractId;
    @NotNull
    private BigDecimal amount;
}
