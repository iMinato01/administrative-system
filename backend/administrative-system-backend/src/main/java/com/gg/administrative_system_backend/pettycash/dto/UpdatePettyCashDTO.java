package com.gg.administrative_system_backend.pettycash.dto;

import com.gg.administrative_system_backend.pettycash.expense.dto.UpdateExpenseDTO;
import com.gg.administrative_system_backend.shared.ExpenseType;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class UpdatePettyCashDTO {
    private ExpenseType type;
    private LocalDate date;
    private List<UpdateExpenseDTO> expenses;
}
