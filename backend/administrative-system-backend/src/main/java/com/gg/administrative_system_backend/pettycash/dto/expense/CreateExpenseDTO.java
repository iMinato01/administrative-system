package com.gg.administrative_system_backend.pettycash.dto.expense;

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
    private String supplier;
    @NotNull
    private String description;
    @NotNull
    private String contract;
    @NotNull
    private BigDecimal total;
}
