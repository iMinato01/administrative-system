package com.gg.administrative_system_backend.pettycash.dto;

import com.gg.administrative_system_backend.shared.ExpenseType;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class UpdatePettyCash {
    private ExpenseType type;
    private LocalDate date;
}
