package com.gg.administrative_system_backend.auth;

import com.gg.administrative_system_backend.company.entity.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Custom implementation of {@link UserDetails} for a Company entity. <br>
 * Wraps a {@link Company} object and exposes its authentication-related data to Spring Security.
 */
@RequiredArgsConstructor
public class CompanyDetails implements UserDetails {
    private final Company company;

    /**
     * Returns the granted authorities (roles) of the company.
     *
     * @return A collection containing the role of the company in the format ROLE_{ROLE_NAME}
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_".concat(company.getRole().name())));
    }

    /**
     * Returns the password used to authenticate the company.
     *
     * @return he company's password.
     */
    public String getPassword() {
        return company.getPassword();
    }

    /**
     * Returns the unique username used for authentication (RFC in this case).
     *
     * @return The company's RFC.
     */
    public String getUsername() {
        return company.getRfc();
    }

    /**
     * Returns the unique identifier of the company.
     *
     * @return The company's ID.
     */
    public Long getId() {
        return company.getId();
    }
}
