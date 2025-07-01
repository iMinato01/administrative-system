package com.gg.administrative_system_backend.supplier.service;

import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.exception.PropertyAlreadyInUseException;
import com.gg.administrative_system_backend.exception.ValueRequiredException;
import com.gg.administrative_system_backend.supplier.message.SupplierMessage;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import com.gg.administrative_system_backend.supplier.repository.SupplierRepository;
import com.gg.administrative_system_backend.util.RegexPatterns;
import com.gg.administrative_system_backend.util.UpdateProperty;
import com.gg.administrative_system_backend.util.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final UpdateProperty updateProperty;
    private final ValidationUtils validationUtils;
    @Transactional
    public Supplier saveSupplier(String name, String rfc, String email, String phoneNumber, String services){
        validationUtils.validateIfExists(name, supplierRepository::existsByName, ()-> new PropertyAlreadyInUseException(SupplierMessage.NAME_ALREADY_IN_USE.format(name)));
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
        Supplier supplier = findSupplier(id);
        updateProperty.updateIfChanged(supplier::getName, name, supplier::setName, supplierRepository::existsByName, () -> new PropertyAlreadyInUseException(SupplierMessage.NAME_ALREADY_IN_USE.format(name)));
        updateProperty.updateIfChanged(supplier::isStatus, status, supplier::setStatus);
        updateProperty.updateIfChanged(supplier::getRfc, rfc, supplier::setRfc, supplierRepository::existsByRfc, ()-> new PropertyAlreadyInUseException(SupplierMessage.RFC_ALREADY_IN_USE.format(rfc)));
        updateProperty.updateIfChanged(supplier::getEmail, email, supplier::setEmail);
        updateProperty.updateIfChanged(supplier::getPhoneNumber, phoneNumber, supplier::setPhoneNumber);
        updateProperty.updateIfChanged(supplier::getServices, services, supplier::setServices);
        return supplier;
    }
    public List<Supplier> findByValue(String value){
        if(value.isBlank()){
            throw new ValueRequiredException(SupplierMessage.VALUE_REQUIRED.getMessage());
        }
        return value.matches(RegexPatterns.LONG)? supplierRepository.findById(Long.parseLong(value)).map(List::of).orElse(List.of()): supplierRepository.findByValue(value);
    }
    public Supplier findSupplier(Long id){
        return supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(SupplierMessage.SUPPLIER_NOT_FOUND.format(id)));
    }
}
