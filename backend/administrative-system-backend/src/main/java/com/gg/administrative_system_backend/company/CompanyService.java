package com.gg.administrative_system_backend.company;

import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.message.CompanyMessage;
import com.gg.administrative_system_backend.util.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ValidationUtils validationUtils;
    @Transactional
    public Company saveCompany(String name, String rfc, String phoneNumber, String state, String municipality, String locality,
                               String postalCode, String street, String interiorNumber, String exteriorNUmber){
        validationUtils.validateIfExists(name, companyRepository::existsByName, () -> new EntityNotFoundException(CompanyMessage.NAME_ALREADY_IN_USE.format(name)));
        validationUtils.validateIfExists(rfc, companyRepository::existsByRfc, () -> new EntityNotFoundException(CompanyMessage.RFC_ALREADY_IN_USE.format(rfc)));
        return companyRepository.save(Company.builder()
                .name(name)
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

    public Company findCompany(Long id){
        return companyRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(CompanyMessage.COMPANY_NOT_FOUND.format(id)));
    }
}
