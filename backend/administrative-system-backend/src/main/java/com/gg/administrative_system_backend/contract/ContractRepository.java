package com.gg.administrative_system_backend.contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query("SELECT C FROM Contract C WHERE CAST(C.id AS string) LIKE %:value% OR " +
            "C.name LIKE %:value% OR CAST(C.totalExpenses AS string) LIKE %:value%")
    List<Contract> findByValue(@Param("value") String value);
    @Query("SELECT C.name FROM Contract C WHERE C.status = %:status%")
    List<String> findOnlyNames(Boolean status);
    boolean existsByName(String name);
}
