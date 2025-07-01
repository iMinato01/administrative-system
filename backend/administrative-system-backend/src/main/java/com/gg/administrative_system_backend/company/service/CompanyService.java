package com.gg.administrative_system_backend.company.service;

import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.company.repository.CompanyRepository;
import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.exception.PropertyAlreadyInUseException;
import com.gg.administrative_system_backend.company.message.CompanyMessage;
import com.gg.administrative_system_backend.util.UpdateProperty;
import com.gg.administrative_system_backend.util.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ValidationUtils validationUtils;
    private final UpdateProperty updateProperty;
    @Transactional
    public Company saveCompany(String name, String rfc, String phoneNumber, String state, String municipality, String locality,
                               String postalCode, String street, String interiorNumber, String exteriorNUmber){
        validationUtils.validateIfExists(name, companyRepository::existsByName, () -> new EntityNotFoundException(CompanyMessage.NAME_ALREADY_IN_USE.format(name)));
        validationUtils.validateIfExists(rfc, companyRepository::existsByRfc, () -> new EntityNotFoundException(CompanyMessage.RFC_ALREADY_IN_USE.format(rfc)));
        return companyRepository.save(Company.builder()
                .name(name)
                .rfc(rfc)
                .phoneNumber(phoneNumber)
                .state(state)
                .municipality(municipality)
                .locality(locality)
                .postalCode(postalCode)
                .street(street)
                .interiorNumber(interiorNumber)
                .exteriorNumber(exteriorNUmber)
                .build());
    }
    @Transactional
    public Company updateCompany(Long id, String name, Boolean status, String rfc, String phoneNumber, String state, String municipality, String locality,
                                 String postalCode, String street, String interiorNumber, String exteriorNUmber){
        Company company = findCompany(id);
        updateProperty.updateIfChanged(company::getName, name, company::setName, companyRepository::existsByName, () -> new PropertyAlreadyInUseException(CompanyMessage.NAME_ALREADY_IN_USE.format(name)));
        updateProperty.updateIfChanged(company::isStatus, status, company::setStatus);
        updateProperty.updateIfChanged(company::getRfc, rfc, company::setRfc, companyRepository::existsByRfc, ()-> new PropertyAlreadyInUseException(CompanyMessage.RFC_ALREADY_IN_USE.format(rfc)));
        updateProperty.updateIfChanged(company::getPhoneNumber, phoneNumber, company::setPhoneNumber);
        updateProperty.updateIfChanged(company::getState, state, company::setState);
        updateProperty.updateIfChanged(company::getMunicipality, municipality, company::setMunicipality);
        updateProperty.updateIfChanged(company::getLocality, locality, company::setLocality);
        updateProperty.updateIfChanged(company::getPostalCode, postalCode, company::setPostalCode);
        updateProperty.updateIfChanged(company::getStreet, street, company::setStreet);
        updateProperty.updateIfChanged(company::getInteriorNumber, interiorNumber, company::setInteriorNumber);
        updateProperty.updateIfChanged(company::getExteriorNumber, exteriorNUmber, company::setExteriorNumber);
        return company;
    }

    public Company findCompany(Long id){
        return companyRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(CompanyMessage.COMPANY_NOT_FOUND.format(id)));
    }
}
