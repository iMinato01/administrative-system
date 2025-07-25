package com.gg.administrative_system_backend.pettycash.dto.pettycash;

import com.gg.administrative_system_backend.pettycash.dto.expense.CreateExpenseDTO;
import com.gg.administrative_system_backend.shared.ExpenseType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreatePettyCashDTO {
    @NotNull
    private ExpenseType type;
    @NotEmpty
    private List<CreateExpenseDTO> expenses;
}
