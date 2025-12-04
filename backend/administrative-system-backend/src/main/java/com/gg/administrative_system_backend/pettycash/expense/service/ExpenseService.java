package com.gg.administrative_system_backend.pettycash.expense.service;

import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.pettycash.expense.entity.Expense;
import com.gg.administrative_system_backend.pettycash.expense.repository.ExpenseRepository;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.shared.message.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    public Expense findExpense(Long id){
        return expenseRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ExceptionMessage.ENTITY_NOT_FOUND.format(Report.EXPENSE.getName(), id)));
    }
}
