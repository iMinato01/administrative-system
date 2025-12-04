package com.gg.administrative_system_backend.evaluation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CreateEvaluationDTO {
    @NotNull
    @NumberFormat
    private Long supplierId;
    @NotNull
    @DateTimeFormat(pattern = RegexPatterns.DATE)
    @JsonFormat(pattern = RegexPatterns.DATE)
    private LocalDate evaluationDate;
    @NotNull
    @DateTimeFormat(pattern = RegexPatterns.DATE)
    @JsonFormat(pattern = RegexPatterns.DATE)
    private LocalDate nextEvaluation;
    @NotEmpty
    private List<Integer> informationScores;
    @NotEmpty
    private List<Integer> generalScores;
    @NotEmpty
    private List<Integer> deliveryScores;
    @NotEmpty
    private List<Integer> qualityScores;
}
