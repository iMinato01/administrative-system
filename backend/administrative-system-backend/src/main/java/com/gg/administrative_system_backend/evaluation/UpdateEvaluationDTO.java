package com.gg.administrative_system_backend.evaluation;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateEvaluationDTO {
    @NotNull
    private LocalDate evaluationDate;

}
