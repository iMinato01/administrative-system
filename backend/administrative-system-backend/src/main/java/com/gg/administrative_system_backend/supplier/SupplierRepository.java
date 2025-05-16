package com.gg.administrative_system_backend.supplier;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Boolean existsByName(String name);
    Boolean existsByRfc(String rfc);
}
