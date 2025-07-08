package com.gg.administrative_system_backend.evaluation.service;

import com.gg.administrative_system_backend.evaluation.dto.CreateEvaluationDTO;
import com.gg.administrative_system_backend.evaluation.dto.UpdateEvaluationDTO;
import com.gg.administrative_system_backend.evaluation.entity.Evaluation;
import com.gg.administrative_system_backend.evaluation.mapper.EvaluationMapper;
import com.gg.administrative_system_backend.evaluation.repository.EvaluationRepository;
import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import com.gg.administrative_system_backend.shared.message.LabelEntity;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import com.gg.administrative_system_backend.supplier.service.SupplierService;
import com.gg.administrative_system_backend.util.RegexPatterns;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Service responsible for managing the registration, updating, filtering, and validation
 * of {@code Evaluation} entities.
 */
@Service
@AllArgsConstructor
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final EvaluationMapper evaluationMapper;
    private final SupplierService supplierService;

    /**
     * Registers a new {@code Evaluation} entity using the properties provided in the DTO.
     * @param createEvaluationDTO DTO containing the properties of the new entity.
     * @return The registered {@code Evaluation} entity.
     */
    @Transactional
    public Evaluation saveEvaluation(CreateEvaluationDTO createEvaluationDTO){
        Supplier supplier = supplierService.findSupplier(createEvaluationDTO.getSupplierId());
        return evaluationRepository.save(evaluationMapper.toEntity(createEvaluationDTO, supplier));
    }

    /**
     * Updates the properties of an existing {@code Evaluation} entity (fully or partially) using its ID
     * and the provided new values.
     * @param id Unique identifier of the {@code Evaluation} entity to update.
     * @param updateEvaluationDTO DTO containing the new properties to update.
     * @return The updated {@code Evaluation} entity.
     */
    @Transactional
    public Evaluation updateEvaluation(Long id, UpdateEvaluationDTO updateEvaluationDTO){
        Evaluation evaluation = findEvaluation(id);
        updateSupplierIfChanged(evaluation, updateEvaluationDTO.getSupplierId());
        return evaluationMapper.updateEntityFromDto(updateEvaluationDTO, evaluation);
    }

    /**
     * Retrieves an {@code Evaluation} entity by its ID.
     * Throws an {@code EntityNotFoundException} if the entity does not exist.
     * @param id Unique identifier of the {@code Evaluation} entity to retrieve.
     * @return The found {@code Evaluation} entity.
     */
    public Evaluation findEvaluation(Long id){
        return evaluationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(GenericMessage.ENTITY_NOT_FOUND.format(LabelEntity.EVALUATION, id)));
    }

    /**
     * Performs a search across the properties of {@code Evaluation} entities based on the input string,
     * interpreting its format to convert it to the appropriate type.
     * @param value Value to search among {@code Evaluation} entity properties.
     * @return A list of {@code Evaluation} entities that match the input.
     */
    public List<Evaluation> findByValue(String value){
        if(value.matches(RegexPatterns.DECIMAL)){
            if(value.matches(RegexPatterns.LONG)){
                return evaluationRepository.findBySupplierId(Long.parseLong(value));
            }
            else if(value.matches(RegexPatterns.DATE_FORMAT)){
                LocalDate date = LocalDate.parse(value);
                return evaluationRepository.findByDate(date);
            }
        }
            return evaluationRepository.findBySupplierName(value);
    }

    /**
     * Updates the relationship between an {@code Evaluation} entity and a {@code Supplier} entity if it has changed.
     * <p>If the provided ID refers to a different {@code Supplier} than the one currently associated with the {@code Evaluation},
     * the {@code Evaluation} is removed from the current supplier's evaluation list, reassigned to the new supplier,
     * and added to the new supplier's list of evaluations.</p>
     * @param evaluation The {@code Evaluation} entity to update.
     * @param id Unique identifier of the {@code Supplier} entity to associate.
     */
    private void updateSupplierIfChanged(Evaluation evaluation, Long id){
        if(id != null){
            Supplier supplier = supplierService.findSupplier(id);
            Supplier currentSupplier = evaluation.getSupplier();
            if(currentSupplier != null && !currentSupplier.equals(supplier)){
                currentSupplier.getEvaluations().remove(evaluation);
                evaluation.setSupplier(supplier);
                supplier.getEvaluations().add(evaluation);
            }
        }
    }
}
