package com.gg.administrative_system_backend.supplier.service;

import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.exception.PropertyAlreadyInUseException;
import com.gg.administrative_system_backend.exception.ValueRequiredException;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import com.gg.administrative_system_backend.supplier.dto.CreateSupplierDTO;
import com.gg.administrative_system_backend.supplier.dto.UpdateSupplierDTO;
import com.gg.administrative_system_backend.supplier.mapper.SupplierMapper;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import com.gg.administrative_system_backend.supplier.repository.SupplierRepository;
import com.gg.administrative_system_backend.util.RegexPatterns;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.util.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service responsible for managing the registration, updating, filtering, and validation
 * of {@code Supplier} entities.
 */
@Service
@AllArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    /**
     * Registers a new {@code Supplier} entity using the properties provided in the DTO.
     * @param createSupplierDTO DTO containing the properties of the new entity.
     * @return The registered {@code Supplier} entity.
     */
    @Transactional
    public Supplier saveSupplier(CreateSupplierDTO createSupplierDTO){
        ValidationUtils.validateIfExists(createSupplierDTO.getName(), supplierRepository::existsByName, ()-> new PropertyAlreadyInUseException(GenericMessage.PROPERTY_IN_USE.format(Report.SUPPLIER.getName(), createSupplierDTO.getName())));
        return supplierRepository.save(supplierMapper.toEntity(createSupplierDTO));
    }

    /**
     * Updates the properties of an existing {@code Supplier} entity (fully or partially) using its ID
     * and the provided new values.
     * @param id Unique identifier of the {@code Supplier} entity to update.
     * @param updateSupplierDTO DTO containing the new properties to update.
     * @return The updated {@code Supplier} entity.
     */
    @Transactional
    public Supplier updateSupplier(Long id, UpdateSupplierDTO updateSupplierDTO){
        Supplier supplier = findSupplier(id);
        ValidationUtils.validateIfExists(updateSupplierDTO.getName(), supplierRepository::existsByName, ()-> new PropertyAlreadyInUseException(GenericMessage.PROPERTY_IN_USE.format(updateSupplierDTO.getName())));
        ValidationUtils.validateIfExists(updateSupplierDTO.getRfc(), supplierRepository::existsByRfc, ()-> new PropertyAlreadyInUseException(GenericMessage.PROPERTY_IN_USE.format(updateSupplierDTO.getRfc())));
        return supplierMapper.updateEntityFromDto(updateSupplierDTO, supplier);
    }

    /**
     * Retrieves a {@code Supplier} entity by its ID.
     * Throws an {@code EntityNotFoundException} if the entity does not exist.
     * @param id Unique identifier of the {@code Supplier} entity to retrieve.
     * @return The found {@code Supplier} entity.
     */
    public Supplier findSupplier(Long id){
        return supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(GenericMessage.ENTITY_NOT_FOUND.format(Report.SUPPLIER.getName(), id)));
    }

    /**
     * Performs a search across the properties of {@code Supplier} entities using an input string,
     * interpreting its format to convert it to the appropriate type.
     * @param value Value to search among {@code Supplier} entity properties.
     * @return A list of {@code Supplier} entities that match the input.
     */
    public List<Supplier> findByValue(String value){
        if(value.isBlank()){
            throw new ValueRequiredException(GenericMessage.VALUE_REQUIRED.getMessage());
        }
        return value.matches(RegexPatterns.LONG)? supplierRepository.findById(Long.parseLong(value)).map(List::of).orElse(List.of()): supplierRepository.findByValue(value);
    }
}
