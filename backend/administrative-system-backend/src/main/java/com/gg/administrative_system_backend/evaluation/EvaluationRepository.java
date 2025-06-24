package com.gg.administrative_system_backend.evaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    @Query("SELECT E.supplier.name FROM Evaluation E WHERE E.id = :id")
    String findSupplierNameByEvaluationId(@Param("id") Long id);
    List<Evaluation> findBySupplierId(Long id);
    @Query("SELECT E FROM Evaluation E WHERE E.evaluationDate = :date OR E.nextEvaluation = :date")
    List<Evaluation> findByDate(@Param("date") LocalDate date);
    @Query("SELECT E FROM Evaluation E WHERE LOWER(E.supplier.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Evaluation> findBySupplierName(@Param("name") String name);
}
