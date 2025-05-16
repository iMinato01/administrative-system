package com.gg.administrative_system_backend.supplier;

import com.gg.administrative_system_backend.exception.PropertyAlreadyInUseException;
import com.gg.administrative_system_backend.message.SupplierMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    @Transactional
    public Supplier saveSupplier(String name, String rfc, String email, String phoneNumber, String services){
        validateName(name, ()-> new PropertyAlreadyInUseException(SupplierMessage.NAME_ALREADY_IN_USE.format(name)));
        if(supplierRepository.existsByRfc(rfc)){
            throw new PropertyAlreadyInUseException(SupplierMessage.RFC_ALREADY_IN_USE.format(rfc));
        }
        return supplierRepository.save(Supplier.builder()
                .name(name)
                .rfc(rfc)
                .email(email)
                .phoneNumber(phoneNumber)
                .services(services)
                .build());
    }
    public void validateName(String name, java.util.function.Supplier<? extends RuntimeException> exception){
        if(supplierRepository.existsByName(name)){
            throw exception.get();
        }
    }
}
