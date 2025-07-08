package com.gg.administrative_system_backend.company.repository;

import com.gg.administrative_system_backend.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    /**
     * Retrieves a collection of {@code Company} entities that share the same {@code name}, {@code rfc}, {@code phoneNumber},
     * {@code state}, {@code municipality}, {@code postalCode}, {@code street}, {@code interiorNumber}, or {@code exteriorNumber}
     * with the given {@code value}.
     * @param value The property value to search for among the entities.
     * @return A list of {@code Company} entities that have properties matching the input {@code value}.
     */
    @Query("SELECT C FROM Company C WHERE C.name LIKE LOWER(CONCAT('%', :value, '%')) OR C.rfc LIKE LOWER(CONCAT('%', :value, '%')) OR C.phoneNumber LIKE LOWER(CONCAT('%', :value, '%')) OR C.state LIKE LOWER(CONCAT('%', :value, '%')) OR C.municipality LIKE LOWER(CONCAT('%', :value, '%')) OR C.postalCode LIKE LOWER(CONCAT('%', :value, '%')) OR C.street LIKE LOWER(CONCAT('%', :value, '%')) OR C.interiorNumber LIKE LOWER(CONCAT('%', :value, '%')) OR C.exteriorNumber LIKE LOWER(CONCAT('%', :value, '%'))")
    List<Company> findByValue(@Param("value") String value);
    Boolean existsByName(String name);
    Boolean existsByRfc(String rfc);
}
