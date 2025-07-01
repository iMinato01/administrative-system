package com.gg.administrative_system_backend.supplier.repository;

import com.gg.administrative_system_backend.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("SELECT S FROM Supplier S WHERE LOWER(S.name) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(S.rfc) LIKE LOWER(CONCAT('%', :value, '%')) OR" +
            " LOWER(S.email) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(S.phoneNumber) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(S.services) LIKE LOWER(CONCAT('%', :value, '%'))")
    List<Supplier> findByValue(@Param("value") String value);
    Boolean existsByName(String name);
    Boolean existsByRfc(String rfc);
}
