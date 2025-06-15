package com.gg.administrative_system_backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EvaluationMessage {
    EVALUATION_NOT_FOUND("La evaluación ID '%s' no existe");
    private final String message;
    public String format(Object... args){
        return String.format(message, args);
    }

}
