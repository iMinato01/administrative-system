package com.gg.administrative_system_backend.contract;

import com.gg.administrative_system_backend.exception.EntityAlreadyExistsException;
import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.exception.PropertyAlreadyInUseException;
import com.gg.administrative_system_backend.exception.ValueRequiredException;
import com.gg.administrative_system_backend.message.ContractMessage;
import com.gg.administrative_system_backend.util.RegexPatterns;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
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
        updateProperty.updateIfChanged(contract::getName, name, contract::setName, contractRepository::existsByName,()-> new PropertyAlreadyInUseException(ContractMessage.NAME_ALREADY_IN_USE.format(name)));
        updateProperty.updateIfChanged(contract::isStatus, status, contract::setStatus);
        updateProperty.updateIfChanged(contract::getTotalExpenses, totalExpenses, contract::setTotalExpenses);
        return contract;
    }
    public List<Contract> findByValue(String value) throws NumberFormatException{
        if(value.isBlank()){
            throw new ValueRequiredException(ContractMessage.VALUE_REQUIRED.getMessage());
        }
        if(value.matches(RegexPatterns.DECIMAL)){
            Set<Contract> contracts = new HashSet<>();
            contracts.addAll(contractRepository.findById(Long.parseLong(value)).map(List::of).orElse(List.of()));
            contracts.addAll(contractRepository.findByTotalExpenses(new BigDecimal(value.replace(",", ""))));
            return new ArrayList<>(contracts);
        } else{
            return contractRepository.findByNameContainingIgnoreCase(value);
        }
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
