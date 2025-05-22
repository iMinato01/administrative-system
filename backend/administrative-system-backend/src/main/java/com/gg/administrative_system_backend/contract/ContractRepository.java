package com.gg.administrative_system_backend.contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByNameContainingIgnoreCase(String name);
    List<Contract> findByTotalExpenses(BigDecimal totalExpenses);
    @Query("SELECT C.name FROM Contract C WHERE C.status = :status")
    List<String> findOnlyNames(Boolean status);
    boolean existsByName(String name);
}
