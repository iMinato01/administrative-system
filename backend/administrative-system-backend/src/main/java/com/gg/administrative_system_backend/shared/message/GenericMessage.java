package com.gg.administrative_system_backend.shared.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenericMessage {
    COMPILE_FAIL("Error al compilar el reporte en %s, path -> %s"),
    COMPILE_SUCCESS("Compilado -> %s"),
    MISSING_PATH("No se encontró el recurso en -> %s"),
    SEPARATOR(" -> "),
    LOGGED("Logueado"),
    SAVED("Guardado");
    private final String message;
    public String format(Object... args){
        return String.format(message, args);
    }
}
