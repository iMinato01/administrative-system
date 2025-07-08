package com.gg.administrative_system_backend.evaluation.mapper;

import com.gg.administrative_system_backend.evaluation.dto.CreateEvaluationDTO;
import com.gg.administrative_system_backend.evaluation.dto.UpdateEvaluationDTO;
import com.gg.administrative_system_backend.evaluation.entity.Evaluation;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import com.gg.administrative_system_backend.util.UpdateUtils;
import org.springframework.stereotype.Component;

/**
 * Class responsible for mapping {@code Evaluation} objects from their DTOs.
 * Used for creating and updating entities based on the data provided in DTOs.
 */
@Component
public class EvaluationMapper {

    /**
     * Maps an {@code Evaluation} entity from a DTO containing the required properties.
     *
     * @param createEvaluationDTO DTO with the properties needed to create the entity.
     * @param supplier Entity that establishes the relationship with the {@code Evaluation} entity.
     * @return An {@code Evaluation} entity built from the DTO.
     */
    public Evaluation toEntity(CreateEvaluationDTO createEvaluationDTO, Supplier supplier){
        return Evaluation.builder()
                .evaluationDate(createEvaluationDTO.getEvaluationDate())
                .nextEvaluation(createEvaluationDTO.getNextEvaluation())
                .supplier(supplier)
                .informationScores(createEvaluationDTO.getInformationScores())
                .generalScores(createEvaluationDTO.getGeneralScores())
                .deliveryScores(createEvaluationDTO.getDeliveryScores())
                .qualityScores(createEvaluationDTO.getQualityScores())
                .build();
    }

    /**
     * Checks for changes in the properties of an existing {@code Evaluation} entity and updates its values
     * with the data from the DTO, if they differ.
     *
     * @param updateEvaluationDTO DTO with the properties to update.
     * @param evaluation Existing {@code Evaluation} entity to be updated.
     * @return The updated {@code Evaluation} entity with the new values from the DTO.
     */
    public Evaluation updateEntityFromDto(UpdateEvaluationDTO updateEvaluationDTO, Evaluation evaluation){
        UpdateUtils.updateIfChanged(evaluation::getEvaluationDate, updateEvaluationDTO::getEvaluationDate, evaluation::setEvaluationDate);
        UpdateUtils.updateIfChanged(evaluation::getNextEvaluation, updateEvaluationDTO::getNextEvaluation, evaluation::setNextEvaluation);
        UpdateUtils.updateIfChanged(evaluation::getInformationScores, updateEvaluationDTO::getInformationScores, evaluation::setInformationScores);
        UpdateUtils.updateIfChanged(evaluation::getGeneralScores, updateEvaluationDTO::getGeneralScores, evaluation::setGeneralScores);
        UpdateUtils.updateIfChanged(evaluation::getDeliveryScores, updateEvaluationDTO::getDeliveryScores, evaluation::setDeliveryScores);
        UpdateUtils.updateIfChanged(evaluation::getQualityScores, updateEvaluationDTO::getQualityScores, evaluation::setQualityScores);
        return evaluation;
    }
}
