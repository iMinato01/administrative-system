package com.gg.administrative_system_backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HandlerExceptionMessage {
    VALIDATION_EXCEPTION("Error en las validaciones"),
    REQUEST_PARAMETER_EXCEPTION("El parámetro %s de tipo %s es requerido"),
    NO_HANDLER_EXCEPTION("La URL no existe o fue mal escrita"),
    MISSING_BODY_EXCEPTION("El cuerpo de la petición está incompleto o no cumple las validaciones"),
    NUMBER_FORMAT_EXCEPTION("Error al convertir el valor -> %s"),
    METHOD_NOT_FOUND("La propiedad '%s' del módulo '%s' no está correctamente manejada");
    private final String message;
    public String format(Object... args){
        return String.format(message, args);
    }
}
