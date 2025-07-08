package com.gg.administrative_system_backend.evaluation.repository;

import com.gg.administrative_system_backend.evaluation.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findBySupplierId(Long id);

    /**
     * Retrieves a collection of {@code Evaluation} entities that match the given {@code evaluationDate}
     * or {@code nextEvaluation} with the specified {@code date} value.
     *
     * @param date Date value to search in the {@code evaluationDate} and {@code nextEvaluation} fields.
     * @return A list of {@code Evaluation} entities with a matching date.
     */
    @Query("SELECT E FROM Evaluation E WHERE E.evaluationDate = :date OR E.nextEvaluation = :date")
    List<Evaluation> findByDate(@Param("date") LocalDate date);

    /**
     * Retrieves a collection of {@code Evaluation} entities whose associated {@code Supplier} name
     * contains the given {@code name} value (case-insensitive).
     *
     * @param name Name value to search within the {@code supplier.name} field.
     * @return A list of {@code Evaluation} entities with a supplier name matching the input.
     */
    @Query("SELECT E FROM Evaluation E WHERE LOWER(E.supplier.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Evaluation> findBySupplierName(@Param("name") String name);
}
