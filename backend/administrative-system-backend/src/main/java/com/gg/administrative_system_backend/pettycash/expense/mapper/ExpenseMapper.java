package com.gg.administrative_system_backend.pettycash.expense.mapper;

import com.gg.administrative_system_backend.contract.entity.Contract;
import com.gg.administrative_system_backend.contract.service.ContractService;
import com.gg.administrative_system_backend.pettycash.expense.dto.CreateExpenseDTO;
import com.gg.administrative_system_backend.pettycash.expense.dto.UpdateExpenseDTO;
import com.gg.administrative_system_backend.pettycash.expense.entity.Expense;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import com.gg.administrative_system_backend.supplier.service.SupplierService;
import com.gg.administrative_system_backend.util.UpdateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@AllArgsConstructor
public class ExpenseMapper {
    private final SupplierService supplierService;
    private final ContractService contractService;

    public Expense toExpense(CreateExpenseDTO createExpenseDTO) {
        Supplier supplier = supplierService.findSupplier(createExpenseDTO.getSupplierId());
        Contract contract = contractService.findContract(createExpenseDTO.getContractId());
        contract.addAmount(createExpenseDTO.getAmount());
        return Expense.builder()
                .date(createExpenseDTO.getDate())
                .supplier(supplier)
                .description(createExpenseDTO.getDescription())
                .contract(contract)
                .amount(createExpenseDTO.getAmount())
                .build();
    }

    public Expense toExpense(UpdateExpenseDTO updateExpenseDTO) {
        Supplier supplier = supplierService.findSupplier(updateExpenseDTO.getSupplierId());
        Contract contract = contractService.findContract(updateExpenseDTO.getContractId());
        contract.addAmount(updateExpenseDTO.getAmount());
        return Expense.builder()
                .date(updateExpenseDTO.getDate())
                .supplier(supplier)
                .description(updateExpenseDTO.getDescription())
                .contract(contract)
                .amount(updateExpenseDTO.getAmount())
                .build();
    }

    public Expense updateEntityFromDto(UpdateExpenseDTO updateExpenseDTO, Expense expense) {
        UpdateUtils.updateIfChanged(expense::getDate, updateExpenseDTO::getDate, expense::setDate);
        UpdateUtils.updateIfChanged(expense::getDescription, updateExpenseDTO::getDescription, expense::setDescription);
        UpdateUtils.updateIfChanged(expense::getAmount, updateExpenseDTO::getAmount, expense::setAmount);
        UpdateUtils.updateIfChanged(expense::getId, updateExpenseDTO::getId, contractService::findContract, expense::setContract);
        UpdateUtils.updateIfChanged(expense::getId, updateExpenseDTO::getId, supplierService::findSupplier, expense::setSupplier);
        return expense;
    }
}
