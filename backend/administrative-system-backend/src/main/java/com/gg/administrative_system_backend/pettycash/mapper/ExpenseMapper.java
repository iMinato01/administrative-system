package com.gg.administrative_system_backend.pettycash.mapper;

import com.gg.administrative_system_backend.contract.entity.Contract;
import com.gg.administrative_system_backend.contract.service.ContractService;
import com.gg.administrative_system_backend.pettycash.dto.CreateExpenseDTO;
import com.gg.administrative_system_backend.pettycash.entity.Expense;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import com.gg.administrative_system_backend.supplier.service.SupplierService;
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
}
