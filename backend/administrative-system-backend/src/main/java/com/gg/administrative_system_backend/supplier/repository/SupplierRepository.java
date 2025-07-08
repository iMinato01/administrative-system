package com.gg.administrative_system_backend.supplier.repository;

import com.gg.administrative_system_backend.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    /**
     * Retrieves a collection of {@code Supplier} entities that match the given input value
     * in any of the following properties: {@code name}, {@code rfc}, {@code email},
     * {@code phoneNumber}, or {@code services}.
     * <p>The search is case-insensitive and supports partial matches.</p>
     * @param value Input string to search across multiple supplier fields.
     * @return A list of {@code Supplier} entities that contain the given value in one of the searchable fields.
     */
    @Query("SELECT S FROM Supplier S WHERE LOWER(S.name) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(S.rfc) LIKE LOWER(CONCAT('%', :value, '%')) OR" +
            " LOWER(S.email) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(S.phoneNumber) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(S.services) LIKE LOWER(CONCAT('%', :value, '%'))")
    List<Supplier> findByValue(@Param("value") String value);
    Boolean existsByName(String name);
    Boolean existsByRfc(String rfc);
}
