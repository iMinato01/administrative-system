package com.gg.administrative_system_backend.pettycash.expense.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
public class UpdateExpenseDTO {
    private Long id;
    private LocalDate date;
    private Long supplierId;
    private String description;
    private Long contractId;
    private BigDecimal amount;
}
