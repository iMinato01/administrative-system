package com.gg.administrative_system_backend.company.repository;

import com.gg.administrative_system_backend.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Boolean existsByName(String name);
    Boolean existsByRfc(String rfc);
}
