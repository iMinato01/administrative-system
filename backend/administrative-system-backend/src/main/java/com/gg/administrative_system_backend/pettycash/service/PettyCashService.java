package com.gg.administrative_system_backend.pettycash.service;

import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.pettycash.dto.CreatePettyCashDTO;
import com.gg.administrative_system_backend.pettycash.dto.UpdatePettyCashDTO;
import com.gg.administrative_system_backend.pettycash.expense.dto.CreateExpenseDTO;
import com.gg.administrative_system_backend.pettycash.expense.dto.UpdateExpenseDTO;
import com.gg.administrative_system_backend.pettycash.expense.entity.Expense;
import com.gg.administrative_system_backend.pettycash.entity.PettyCash;
import com.gg.administrative_system_backend.pettycash.expense.mapper.ExpenseMapper;
import com.gg.administrative_system_backend.pettycash.expense.service.ExpenseService;
import com.gg.administrative_system_backend.pettycash.mapper.PettyCashMapper;
import com.gg.administrative_system_backend.pettycash.repository.PettyCashRepository;
import com.gg.administrative_system_backend.shared.message.ExceptionMessage;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.util.UpdateUtils;
import com.gg.administrative_system_backend.util.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
public class PettyCashService {
    private final PettyCashRepository pettyCashRepository;
    private final PettyCashMapper pettyCashMapper;
    private final ExpenseMapper expenseMapper;
    private final ExpenseService expenseService;
    private final ValidationUtils validationUtils;

    public List<PettyCash> findAll() {
        return pettyCashRepository.findAll();
    }

    @Transactional
    public PettyCash savePettyCash(CreatePettyCashDTO createPettyCashDTO) {
        return pettyCashRepository.save(pettyCashMapper.toPettyCash(createPettyCashDTO));
    }

    @Transactional
    public PettyCash updatePettyCash(UpdatePettyCashDTO updatePettyCashDTO, Long id) {
        PettyCash pettyCash = findPettyCash(id);
        UpdateUtils.updateIfChanged(pettyCash::getType, updatePettyCashDTO::getType, pettyCash::setType);
        UpdateUtils.updateIfChanged(pettyCash::getDate, updatePettyCashDTO::getDate, pettyCash::setDate);
        List<UpdateExpenseDTO> newExpenses = updatePettyCashDTO.getExpenses();
        List<Expense> currentExpenses = pettyCash.getExpenses();
        for (UpdateExpenseDTO expense : newExpenses) {
            Long expenseId = expense.getId();
            if (expenseId != null) {
                Expense currentExpense = expenseService.findExpense(expenseId);
                expenseMapper.updateEntityFromDto(expense, currentExpense);
            } else {
                CreateExpenseDTO createExpenseDTO = expenseMapper.updateToCreate(expense);
                validationUtils.validateFields(createExpenseDTO);
                currentExpenses.add(expenseMapper.toExpense(createExpenseDTO));
            }
            pettyCash.setExpenses(currentExpenses);
        }
        return pettyCash;
    }

    public PettyCash findPettyCash(Long id) {
        return pettyCashRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.ENTITY_NOT_FOUND.format(Report.PETTY_CASH.getName(), id)));
    }
}
