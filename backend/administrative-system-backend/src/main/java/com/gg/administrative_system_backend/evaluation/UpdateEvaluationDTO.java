package com.gg.administrative_system_backend.evaluation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gg.administrative_system_backend.message.ValidationMessage;
import com.gg.administrative_system_backend.util.RegexPatterns;
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
public class UpdateEvaluationDTO {
    @NumberFormat
    private Long id;
    @NumberFormat
    private Long supplierId;
    @DateTimeFormat(pattern = RegexPatterns.DATE)
    @JsonFormat(pattern = RegexPatterns.DATE)
    private LocalDate evaluationDate;
    @DateTimeFormat(pattern = RegexPatterns.DATE)
    @JsonFormat(pattern = RegexPatterns.DATE)
    private LocalDate nextEvaluation;
    private List<Integer> informationScores;
    private List<Integer> generalScores;
    private List<Integer> deliveryScores;
    private List<Integer> qualityScores;
}
