package com.gg.administrative_system_backend.company.mapper;

import com.gg.administrative_system_backend.company.dto.CompanyResponseDTO;
import com.gg.administrative_system_backend.company.dto.CreateCompanyDTO;
import com.gg.administrative_system_backend.company.dto.UpdateCompanyDTO;
import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.shared.Role;
import com.gg.administrative_system_backend.util.UpdateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Class responsible for mapping {@code Company} objects from their DTOs.
 * Used for creating and updating entities based on the data provided in DTOs.
 */
@Component
@RequiredArgsConstructor
public class CompanyMapper {
    private final PasswordEncoder passwordEncoder;
    /**
     * Maps a {@code Company} entity from a DTO containing the required properties.
     * @param createCompanyDTO DTO with the properties needed to create the entity.
     * @return A {@code Company} entity built from the DTO.
     */
    public Company toEntity(CreateCompanyDTO createCompanyDTO){
        return Company.builder()
                .name(createCompanyDTO.getName())
                .serie(createCompanyDTO.getSerie())
                .password(passwordEncoder.encode(createCompanyDTO.getPassword()))
                .role(Role.USER)
                .rfc(createCompanyDTO.getRfc())
                .phoneNumber(createCompanyDTO.getPhoneNumber())
                .state(createCompanyDTO.getState())
                .municipality(createCompanyDTO.getMunicipality())
                .locality(createCompanyDTO.getLocality())
                .postalCode(createCompanyDTO.getPostalCode())
                .street(createCompanyDTO.getStreet())
                .interiorNumber(createCompanyDTO.getInteriorNumber())
                .exteriorNumber(createCompanyDTO.getExteriorNumber())
                .build();
    }

    /**
     * Checks for changes in the properties of an existing {@code Company} entity and updates its values
     * using the DTO data, if they differ.
     * @param updateCompanyDTO DTO with the properties to update.
     * @param company Existing {@code Company} entity to be updated.
     * @return The updated {@code Company} entity with the new values from the DTO.
     */
    public Company updateEntityFromDto(UpdateCompanyDTO updateCompanyDTO, Company company){
        UpdateUtils.updateIfChanged(updateCompanyDTO::getName, company::getName, company::setName);
        UpdateUtils.updateIfChanged(updateCompanyDTO::getSerie, company::getSerie, company::setSerie);
        UpdateUtils.updateIfChanged(updateCompanyDTO::getRfc, company::getRfc, company::setRfc);
        UpdateUtils.updateIfChanged(updateCompanyDTO::isStatus, company::isStatus, company::setStatus);
        UpdateUtils.updateIfChanged(updateCompanyDTO::getPhoneNumber, company::getPhoneNumber, company::setPhoneNumber);
        UpdateUtils.updateIfChanged(updateCompanyDTO::getState, company::getState, company::setState);
        UpdateUtils.updateIfChanged(updateCompanyDTO::getMunicipality, company::getMunicipality, company::setMunicipality);
        UpdateUtils.updateIfChanged(updateCompanyDTO::getLocality, company::getLocality, company::setLocality);
        UpdateUtils.updateIfChanged(updateCompanyDTO::getPostalCode, company::getPostalCode, company::setPostalCode);
        UpdateUtils.updateIfChanged(updateCompanyDTO::getStreet, company::getStreet, company::setStreet);
        UpdateUtils.updateIfChanged(updateCompanyDTO::getInteriorNumber, company::getInteriorNumber, company::setInteriorNumber);
        UpdateUtils.updateIfChanged(updateCompanyDTO::getExteriorNumber, company::getExteriorNumber, company::setExteriorNumber);
        return company;
    }

    /**
     * Maps a {@code Company} entity to a {@code CompanyResponseDTO},
     * excluding sensitive data such as password and role.
     *
     * @param company the existing {@code Company} entity
     * @return a {@code CompanyResponseDTO} containing only non-sensitive data
     */

    public CompanyResponseDTO toResponse(Company company){
        return CompanyResponseDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .serie(company.getSerie())
                .fol(company.getFol())
                .status(company.isStatus())
                .rfc(company.getRfc())
                .phoneNumber(company.getPhoneNumber())
                .state(company.getState())
                .municipality(company.getMunicipality())
                .locality(company.getLocality())
                .postalCode(company.getPostalCode())
                .street(company.getStreet())
                .interiorNumber(company.getInteriorNumber())
                .exteriorNumber(company.getExteriorNumber())
                .build();
    }
}
