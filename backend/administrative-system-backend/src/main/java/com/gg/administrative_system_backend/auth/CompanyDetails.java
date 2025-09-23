package com.gg.administrative_system_backend.auth;

import com.gg.administrative_system_backend.company.entity.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CompanyDetails implements UserDetails {
    private final Company company;
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_".concat(company.getRole().name())));
    }
    public String getPassword(){
        return company.getPassword();
    }
    public String getUsername(){
        return company.getRfc();
    }
}
