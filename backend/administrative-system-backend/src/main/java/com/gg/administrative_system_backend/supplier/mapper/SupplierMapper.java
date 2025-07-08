package com.gg.administrative_system_backend.supplier.mapper;

import com.gg.administrative_system_backend.supplier.dto.CreateSupplierDTO;
import com.gg.administrative_system_backend.supplier.dto.UpdateSupplierDTO;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import com.gg.administrative_system_backend.util.UpdateUtils;
import org.springframework.stereotype.Component;

/**
 * Class responsible for mapping {@code Supplier} objects from their DTOs.
 * Used for creating and updating entities based on the data provided in DTOs.
 */
@Component
public class SupplierMapper {

    /**
     * Maps a {@code Supplier} entity from a DTO containing the required properties.
     * @param createSupplierDTO DTO with the properties needed to create the entity.
     * @return A {@code Supplier} entity built from the DTO.
     */
    public Supplier toEntity(CreateSupplierDTO createSupplierDTO){
        return Supplier.builder()
                .name(createSupplierDTO.getName())
                .rfc(createSupplierDTO.getRfc())
                .email(createSupplierDTO.getEmail())
                .phoneNumber(createSupplierDTO.getPhoneNumber())
                .services(createSupplierDTO.getServices())
                .build();
    }

    /**
     * Checks for changes in the properties of an existing {@code Supplier} entity and updates its values
     * with the data from the DTO, if they differ.
     * @param updateSupplierDTO DTO with the properties to update.
     * @param supplier Existing {@code Supplier} entity to be updated.
     * @return The updated {@code Supplier} entity with the new values from the DTO.
     */
    public Supplier updateEntityFromDto(UpdateSupplierDTO updateSupplierDTO, Supplier supplier){
        UpdateUtils.updateIfChanged(supplier::getName, updateSupplierDTO::getName, supplier::setName);
        UpdateUtils.updateIfChanged(supplier::getRfc, updateSupplierDTO::getRfc, supplier::setRfc);
        UpdateUtils.updateIfChanged(supplier::isStatus, updateSupplierDTO::isStatus, supplier::setStatus);
        UpdateUtils.updateIfChanged(supplier::getEmail, updateSupplierDTO::getEmail, supplier::setEmail);
        UpdateUtils.updateIfChanged(supplier::getPhoneNumber, updateSupplierDTO::getPhoneNumber, supplier::setPhoneNumber);
        UpdateUtils.updateIfChanged(supplier::getServices, updateSupplierDTO::getServices, supplier::setServices);
        return supplier;
    }
}
