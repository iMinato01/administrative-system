package com.gg.administrative_system_backend.auth;

import com.gg.administrative_system_backend.company.repository.CompanyRepository;
import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyDetailsService implements UserDetailsService {
    private final CompanyRepository companyRepository;
    @Override
    public CompanyDetails loadUserByUsername(String name){
        return new CompanyDetails(companyRepository.findByName(name).orElseThrow(()-> new EntityNotFoundException(GenericMessage.ENTITY_NOT_FOUND.format(Report.COMPANY.getName(), name))));
    }
}
