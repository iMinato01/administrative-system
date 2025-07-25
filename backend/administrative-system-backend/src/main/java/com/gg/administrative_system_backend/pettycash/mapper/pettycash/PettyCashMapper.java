package com.gg.administrative_system_backend.pettycash.mapper.pettycash;

import com.gg.administrative_system_backend.pettycash.dto.pettycash.CreatePettyCashDTO;
import com.gg.administrative_system_backend.pettycash.entity.expense.Expense;
import com.gg.administrative_system_backend.pettycash.entity.pettycash.PettyCash;
import com.gg.administrative_system_backend.pettycash.mapper.expense.ExpenseMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PettyCashMapper {
    private final ExpenseMapper expenseMapper;
    public PettyCash toPettyCash(CreatePettyCashDTO createPettyCashDTO){
        PettyCash pettyCash = PettyCash.builder().build();
        List<Expense> expenses = createPettyCashDTO.getExpenses().stream().map(expenseMapper::toExpense).peek(expense -> expense.setPettyCash(pettyCash)).toList();
        pettyCash.setExpenses(expenses);
        pettyCash.setType(createPettyCashDTO.getType());
        pettyCash.setTotal(pettyCash.calculateTotal());
        return pettyCash;
    }
}
