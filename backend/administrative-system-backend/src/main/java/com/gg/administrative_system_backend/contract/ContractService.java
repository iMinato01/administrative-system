package com.gg.administrative_system_backend.contract;

import com.gg.administrative_system_backend.exception.EntityAlreadyExistsException;
import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.exception.PropertyAlreadyInUseException;
import com.gg.administrative_system_backend.message.ContractMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    @Transactional
    public Contract saveContract(String name){
        if(contractRepository.existsByName(name)){
            throw new EntityAlreadyExistsException(ContractMessage.CONTRACT_ALREADY_EXISTS.format(name));
        }
        return contractRepository.save(Contract.builder()
                .name(name)
                .build());
    }
    @Transactional
    public Contract updateContract(long id, String name, boolean status, double totalExpenses){
        Contract contract = contractRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ContractMessage.CONTRACT_NOT_FOUND.format(id)));
            boolean isUpdated = false;
            if (!contract.getName().equals(name)) {
                if(contractRepository.existsByName(name)){
                    throw new PropertyAlreadyInUseException(ContractMessage.NAME_ALREADY_IN_USE.format(name));
                }
                contract.setName(name);
                isUpdated = true;
            }
            if(contract.isStatus() != status){
                contract.setStatus(status);
                isUpdated = true;
            }
            if(contract.getTotalExpenses() != totalExpenses){
                contract.setTotalExpenses(totalExpenses);
                isUpdated = true;
            }
            if (isUpdated) {
                contractRepository.save(contract);
            }
        return contract;
    }
}
