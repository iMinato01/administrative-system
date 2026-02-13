package com.gg.administrative_system_backend.pettycash.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ExpensesByContractDTO {
    private String contract;
    private BigDecimal amount;
}
