package com.gg.administrative_system_backend.shared.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenericMessage {
    ENTITY_ALREADY_EXISTS("La entidad %s ya existe"),
    ENTITY_NOT_FOUND("La entidad %s con valor %s no fue encontrada"),
    PROPERTY_IN_USE("El valor %s ya está siendo usado por otra entidad"),
    VALUE_REQUIRED("El valor de entrada no puede estar vacío");
    private final String message;
    public String format(Object... args){
        return String.format(message, args);
    }
}
