package com.gg.administrative_system_backend.evaluation;

import com.gg.administrative_system_backend.message.ValidationMessage;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class CreateEvaluationDTO {
    @NotNull(message = ValidationMessage.ID_REQUIRED)
    @NumberFormat
    private Long supplierId;
    @NotNull(message = ValidationMessage.DATE_REQUIRED)
    @DateTimeFormat(pattern = ValidationMessage.DATE_FORMAT)
    private LocalDate evaluationDate;
    @NotNull(message = ValidationMessage.DATE_REQUIRED)
    @DateTimeFormat(pattern = ValidationMessage.DATE_FORMAT)
    private LocalDate nextEvaluation;
    @NotEmpty(message = ValidationMessage.ELEMENT_REQUIRED)
    private List<Integer> informationScores;
    @NotEmpty(message = ValidationMessage.ELEMENT_REQUIRED)
    private List<Integer> generalScores;
    @NotEmpty(message = ValidationMessage.ELEMENT_REQUIRED)
    private List<Integer> deliveryScores;
    @NotEmpty(message = ValidationMessage.ELEMENT_REQUIRED)
    private List<Integer> qualityScores;
}
