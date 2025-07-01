package com.gg.administrative_system_backend.contract.repository;

import com.gg.administrative_system_backend.contract.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByNameContainingIgnoreCase(String name);
    List<Contract> findByTotalExpenses(BigDecimal totalExpenses);
    @Query("SELECT C.name FROM Contract C WHERE C.status = :status")
    List<String> findOnlyNames(Boolean status);
    boolean existsByName(String name);
}
