package com.gg.administrative_system_backend.contract;

import com.gg.administrative_system_backend.exception.EntityAlreadyExistsException;
import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.exception.PropertyAlreadyInUseException;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
    private final ContractRepository contractRepository;
    public ContractService(ContractRepository contractRepository){
        this.contractRepository = contractRepository;
    }
    public Contract saveContract(String name){
        if(contractRepository.existsByName(name)){
            throw new EntityAlreadyExistsException("El contrato '" + name + "' ya existe");
        }
        return contractRepository.save(new Contract(name));
    }
    public Contract updateContract(long id, String name, boolean status, float totalExpenses){
        Contract contract = contractRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("El contrato no está registrado"));
            boolean isUpdated = false;
            if (!contract.getName().equals(name)) {
                if(contractRepository.existsByName(name)){
                    throw new PropertyAlreadyInUseException("El contrato " + name + " ya existe");
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
