package com.gg.administrative_system_backend.pettycash.mapper.expense;

import com.gg.administrative_system_backend.pettycash.dto.expense.CreateExpenseDTO;
import com.gg.administrative_system_backend.pettycash.entity.expense.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    public Expense toExpense(CreateExpenseDTO createExpenseDTO) {
        return Expense.builder()
                .date(createExpenseDTO.getDate())
                .supplier(createExpenseDTO.getSupplier())
                .description(createExpenseDTO.getDescription())
                .contract(createExpenseDTO.getContract())
                .total(createExpenseDTO.getTotal())
                .build();
    }
}
