package com.gg.administrative_system_backend.contract.service;

import com.gg.administrative_system_backend.contract.dto.CreateContractDTO;
import com.gg.administrative_system_backend.contract.dto.UpdateContractDTO;
import com.gg.administrative_system_backend.contract.entity.Contract;
import com.gg.administrative_system_backend.contract.mapper.ContractMapper;
import com.gg.administrative_system_backend.contract.repository.ContractRepository;
import com.gg.administrative_system_backend.exception.EntityAlreadyExistsException;
import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.exception.PropertyAlreadyInUseException;
import com.gg.administrative_system_backend.exception.ValueRequiredException;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import com.gg.administrative_system_backend.util.RegexPatterns;
import com.gg.administrative_system_backend.util.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Service responsible for managing the creation, update, filtering, and validation of {@code Contract} entities.
 */
@Service
@AllArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    /**
     * Registers a new {@code Contract} entity using the properties provided in the DTO.
     * @param createContractDTO DTO containing the properties of the new entity.
     * @return The registered {@code Contract} entity.
     */
    @Transactional
    public Contract saveContract(CreateContractDTO createContractDTO){
        ValidationUtils.validateIfExists(createContractDTO.getName(), contractRepository::existsByName, ()-> new EntityAlreadyExistsException(GenericMessage.ENTITY_ALREADY_EXISTS.format(createContractDTO.getName())));
        return contractRepository.save(contractMapper.toEntity(createContractDTO));
    }

    /**
     * Updates the properties of a {@code Contract} entity (fully or partially) using its ID
     * and the provided new values.
     * @param id Unique identifier of the {@code Contract} entity to be updated.
     * @param updateContractDTO DTO containing the properties to update.
     * @return The updated {@code Contract} entity.
     */
    @Transactional
    public Contract updateContract(Long id, UpdateContractDTO updateContractDTO){
        Contract contract = findContract(id);
        ValidationUtils.validateIfExists(updateContractDTO.getName(), contractRepository::existsByName, ()-> new PropertyAlreadyInUseException(GenericMessage.PROPERTY_IN_USE.format(updateContractDTO.getName())));
        return contractMapper.updateEntityFromDto(updateContractDTO, contract);
    }

    /**
     * Retrieves a collection of all existing {@code Contract} entities in the database.
     * @return A list of {@code Contract} entities.
     */
    public List<Contract> findAll(){
        return contractRepository.findAll();
    }

    /**
     * Retrieves a {@code Contract} entity by its ID.
     * Throws an {@code EntityNotFoundException} if the entity does not exist.
     * @param id Unique identifier of the {@code Contract} entity to retrieve.
     * @return The found {@code Contract} entity.
     */
    public Contract findContract(Long id){
        return contractRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(GenericMessage.ENTITY_NOT_FOUND.format(id)));
    }

    /**
     * Retrieves a collection of {@code Contract} names from the database filtered by their status.
     * @param status Status used to filter the entities.
     * @return A list of {@code String} containing the names of {@code Contract} entities matching the given status.
     */
    public List<String> findNamesByStatus(boolean status){
        return contractRepository.findOnlyNames(status);
    }

    /**
     * Performs a search across the properties of {@code Contract} entities using an input string,
     * interpreting its format to convert it to the appropriate type.
     * @param value Value to search among the properties of {@code Contract} entities.
     * @return A list of matching {@code Contract} entities.
     */
    public List<Contract> findByValue(String value){
        if(value.isBlank()){
            throw new ValueRequiredException(GenericMessage.VALUE_REQUIRED.getMessage());
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
}
