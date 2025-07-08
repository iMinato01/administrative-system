package com.gg.administrative_system_backend.contract.mapper;

import com.gg.administrative_system_backend.contract.dto.CreateContractDTO;
import com.gg.administrative_system_backend.contract.dto.UpdateContractDTO;
import com.gg.administrative_system_backend.contract.entity.Contract;
import com.gg.administrative_system_backend.util.UpdateUtils;
import org.springframework.stereotype.Component;

/**
 * Class responsible for mapping {@code Contract} objects from their DTOs.
 * Used for creating and updating entities based on the data provided in DTOs.
 */
@Component
public class ContractMapper {

    /**
     * Maps a {@code Contract} entity from a DTO containing the required properties.
     * @param createContractDTO DTO with the properties needed to create the entity.
     * @return A {@code Contract} entity built from the DTO.
     */
    public Contract toEntity(CreateContractDTO createContractDTO){
        return Contract.builder()
                .name(createContractDTO.getName())
                .build();
    }

    /**
     * Checks for changes in the properties of an existing {@code Contract} entity and updates its values
     * with the data from the DTO, if they differ.
     * @param updateContractDTO DTO with the properties to update.
     * @param contract Existing {@code Contract} entity to be updated.
     * @return The updated {@code Contract} entity with the new values from the DTO.
     */
    public Contract updateEntityFromDto(UpdateContractDTO updateContractDTO, Contract contract){
        UpdateUtils.updateIfChanged(contract::getName, updateContractDTO::getName, contract::setName);
        UpdateUtils.updateIfChanged(contract::isStatus, updateContractDTO::getStatus, contract::setStatus);
        UpdateUtils.updateIfChanged(contract::getTotalExpenses, contract::getTotalExpenses, contract::setTotalExpenses);
        return contract;
    }
}
