package com.gg.administrative_system_backend.contract;

import com.gg.administrative_system_backend.exception.EntityAlreadyExistsException;
import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.exception.PropertyAlreadyInUseException;
import com.gg.administrative_system_backend.message.ContractMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    @Transactional
    public Contract saveContract(String name){
        validateName(name, () -> new EntityAlreadyExistsException(ContractMessage.CONTRACT_ALREADY_EXISTS.format(name)));
        return contractRepository.save(Contract.builder()
                .name(name)
                .build());
    }
    @Transactional
    public Contract updateContract(long id, String name, boolean status, BigDecimal totalExpenses){
        Contract contract = contractRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ContractMessage.CONTRACT_NOT_FOUND.format(id)));
        if(nameChanged(contract, name) || statusChanged(contract, status) || totalExpensesChanged(contract, totalExpenses)){
            return contractRepository.save(contract);
        }
        return contract;
    }
    private void validateName(String name, Supplier<? extends RuntimeException> exception){
        if(contractRepository.existsByName(name)){
            throw exception.get();
        }
    }
    private boolean nameChanged(Contract contract, String name){
        if(!contract.getName().equals(name)){
            validateName(name, () -> new PropertyAlreadyInUseException(ContractMessage.NAME_ALREADY_IN_USE.format(name)));
            contract.setName(name);
            return true;
        }
        return false;
    }
    private boolean statusChanged(Contract contract, boolean status){
        if(contract.isStatus() != status){
            contract.setStatus(status);
            return true;
        }
        return false;
    }
    private boolean totalExpensesChanged(Contract contract, BigDecimal totalExpenses){
        if(contract.getTotalExpenses().compareTo(totalExpenses) !=0){
            contract.setTotalExpenses(totalExpenses);
            return true;
        }
        return false;
    }
}
