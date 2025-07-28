package com.gg.administrative_system_backend.pettycash.mapper;

import com.gg.administrative_system_backend.pettycash.dto.CreateExpenseDTO;
import com.gg.administrative_system_backend.pettycash.entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    public Expense toExpense(CreateExpenseDTO createExpenseDTO) {
        return Expense.builder()
                .date(createExpenseDTO.getDate())
                .supplier(createExpenseDTO.getSupplier())
                .description(createExpenseDTO.getDescription())
                .contract(createExpenseDTO.getContract())
                .amount(createExpenseDTO.getAmount())
                .build();
    }
}
