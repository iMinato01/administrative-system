package com.gg.administrative_system_backend.supplier;

import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.exception.PropertyAlreadyInUseException;
import com.gg.administrative_system_backend.exception.ValueRequiredException;
import com.gg.administrative_system_backend.message.ContractMessage;
import com.gg.administrative_system_backend.message.SupplierMessage;
import com.gg.administrative_system_backend.util.RegexPatterns;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    @Transactional
    public Supplier saveSupplier(String name, String rfc, String email, String phoneNumber, String services){
        validateName(name, ()-> new PropertyAlreadyInUseException(SupplierMessage.NAME_ALREADY_IN_USE.format(name)));
        validateRfc(rfc, () -> new PropertyAlreadyInUseException(SupplierMessage.RFC_ALREADY_IN_USE.format(rfc)));
        return supplierRepository.save(Supplier.builder()
                .name(name)
                .rfc(rfc)
                .email(email)
                .phoneNumber(phoneNumber)
                .services(services)
                .build());
    }
    @Transactional
    public Supplier updateSupplier(Long id, String name, Boolean status, String rfc, String email, String phoneNumber, String services){
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(SupplierMessage.SUPPLIER_NOT_FOUND.format(id)));
        if(nameChanged(supplier, name) || statusChanged(supplier, status)|| rfcChanged(supplier, rfc) || emailChanged(supplier, email) ||
                phoneNumberChanged(supplier, phoneNumber) || servicesChanged(supplier, services)) {
            supplierRepository.save(supplier);
        }
        return supplier;
    }
    public List<Supplier> findByValue(String value){
        if(value.isBlank()){
            throw new ValueRequiredException(SupplierMessage.VALUE_REQUIRED.getMessage());
        }
        return value.matches(RegexPatterns.LONG)? supplierRepository.findById(Long.parseLong(value)).map(List::of).orElse(List.of()): supplierRepository.findByValue(value);
    }
    private boolean nameChanged(Supplier supplier, String name){
        if(!supplier.getName().equals(name)){
            validateName(name, ()-> new PropertyAlreadyInUseException(SupplierMessage.NAME_ALREADY_IN_USE.format(name)));
            supplier.setName(name);
            return true;
        }
        return false;
    }
    private boolean statusChanged(Supplier supplier, Boolean status){
        return supplier.isStatus() != status;
    }
    private boolean rfcChanged(Supplier supplier, String rfc){
        if(!supplier.getRfc().equals(rfc)){
            validateRfc(rfc, ()-> new PropertyAlreadyInUseException(SupplierMessage.RFC_ALREADY_IN_USE.format(rfc)));
            supplier.setRfc(rfc);
            return true;
        }
        return false;
    }
    public boolean emailChanged(Supplier supplier, String email){
        return !supplier.getEmail().equals(email);
    }
    public boolean phoneNumberChanged(Supplier supplier, String phoneNumber){
        return !supplier.getPhoneNumber().equals(phoneNumber);
    }
    public boolean servicesChanged(Supplier supplier, String services){
        return !supplier.getServices().equals(services);
    }
    private void validateName(String name, java.util.function.Supplier<? extends RuntimeException> exception){
        if(supplierRepository.existsByName(name)){
            throw exception.get();
        }
    }
    private void validateRfc(String rfc, java.util.function.Supplier<? extends RuntimeException> exception){
        if(supplierRepository.existsByRfc(rfc)){
            throw exception.get();
        }
    }
}
