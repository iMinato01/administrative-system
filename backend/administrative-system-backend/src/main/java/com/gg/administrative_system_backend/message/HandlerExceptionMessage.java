package com.gg.administrative_system_backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HandlerExceptionMessage {
    VALIDATION_EXCEPTION("Error en las validaciones"),
    REQUEST_PARAMETER_EXCEPTION("El parámetro %s de tipo %s es requerido"),
    NO_HANDLER_EXCEPTION("La URL no existe o fue mal escrite"),
    MISSING_BODY_EXCEPTION("El cuerpo de la petición es requerido");

    private final String message;
    public String format(Object... args){
        return String.format(message, args);
    }
}
