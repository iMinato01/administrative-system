package com.gg.administrative_system_backend.pettycash.dto;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String supplier;
    @NotEmpty
    private String description;
    @NotEmpty
    private String contract;
    @NotNull
    private BigDecimal amount;
}
