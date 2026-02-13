package com.gg.administrative_system_backend.company.service;

import com.gg.administrative_system_backend.company.dto.CompanyResponseDTO;
import com.gg.administrative_system_backend.company.dto.CreateCompanyDTO;
import com.gg.administrative_system_backend.company.dto.UpdateCompanyDTO;
import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.company.mapper.CompanyMapper;
import com.gg.administrative_system_backend.company.repository.CompanyRepository;
import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.exception.PropertyAlreadyInUseException;
import com.gg.administrative_system_backend.exception.ValueRequiredException;
import com.gg.administrative_system_backend.shared.message.ExceptionMessage;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import com.gg.administrative_system_backend.util.RegexPatterns;
import com.gg.administrative_system_backend.shared.report.ReportRoute;
import com.gg.administrative_system_backend.util.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service responsible for managing the creation, update, filtering, and validation of {@code Company} entities.
 */
@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    /**
     * Registers a new {@code Company} entity using the properties provided in the DTO.
     * @param createCompanyDTO DTO containing the properties of the new entity.
     * @return {@code String} status message
     */
    @Transactional
    public String saveCompany(CreateCompanyDTO createCompanyDTO){
        ValidationUtils.validateIfExists(createCompanyDTO.getName(), companyRepository::existsByName, () -> new PropertyAlreadyInUseException(ExceptionMessage.PROPERTY_ALREADY_IN_USE.format(createCompanyDTO.getName())));
        ValidationUtils.validateIfExists(createCompanyDTO.getRfc(), companyRepository::existsByRfc, () -> new PropertyAlreadyInUseException(ExceptionMessage.PROPERTY_ALREADY_IN_USE.format(createCompanyDTO.getRfc())));
        companyRepository.save(companyMapper.toEntity(createCompanyDTO));
        return GenericMessage.SAVED.getMessage();
    }

    /**
     * Updates the properties of a {@code Company} entity (fully or partially) using its ID
     * and the provided new values.
     * @param id Unique identifier of the {@code Company} entity to be updated.
     * @param updateCompanyDTO DTO containing the properties to update.
     * @return {@code String} status message
     */
    @Transactional
    public String updateCompany(Long id, UpdateCompanyDTO updateCompanyDTO){
        Company company = findCompany(id);
        ValidationUtils.validateIfExists(updateCompanyDTO.getName(), companyRepository::existsByName, ()-> new PropertyAlreadyInUseException(ExceptionMessage.PROPERTY_ALREADY_IN_USE.format(updateCompanyDTO.getName())));
        ValidationUtils.validateIfExists(updateCompanyDTO.getRfc(), companyRepository::existsByRfc, ()-> new PropertyAlreadyInUseException(ExceptionMessage.PROPERTY_ALREADY_IN_USE.format(updateCompanyDTO.getRfc())));
        companyMapper.updateEntityFromDto(updateCompanyDTO, company);
        return GenericMessage.UPDATED.getMessage();
    }

    /**
     * Retrieves a collection of all existing {@code Company} entities in the database.
     * @return A list of {@code CompanyResponseDTO}.
     */
    public List<CompanyResponseDTO> findAll(){
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(companyMapper::toResponse).toList();
    }

    /**
     * Retrieves a {@code Company} entity by its ID.
     * Throws an {@code EntityNotFoundException} if the entity does not exist.
     * @param id Unique identifier of the {@code Company} entity to retrieve.
     * @return The found {@code Company} entity.
     */
    public Company findCompany(Long id){
        return companyRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ExceptionMessage.ENTITY_NOT_FOUND.format(ReportRoute.COMPANY.getName(), id)));
    }

    /**
     * Performs a search across the properties of {@code Company} entities using an input string,
     * interpreting its format to convert it to the appropriate type.
     * @param value Value to search among the properties of {@code Company} entities.
     * @return A list of matching {@code Company} entities.
     */
    public List<Company> findByValue(String value){
        if(value.isBlank()){
            throw new ValueRequiredException(ExceptionMessage.VALUE_REQUIRED.getMessage());
        }
        if(value.matches(RegexPatterns.LONG)){
            Long id = Long.parseLong(value);
           return List.of(findCompany(id));
        } else{
            return companyRepository.findByValue(value);
        }
    }
}
