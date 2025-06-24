package com.gg.administrative_system_backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContractMessage {
    LIST_ALL("Todos los contratos"),
    LIST_NAMES("Nombres de contratos %s"),
    LIST_BY_VALUE("Contratos filtrados con valor '%s'"),
    CREATED_SUCCESS("El contrato '%s' ha sido guardado"),
    UPDATED_SUCCESS("El contrato '%s' ha sido actualizado"),
    CONTRACT_ALREADY_EXISTS("El contrato '%s' ya existe"),
    CONTRACT_NOT_FOUND("El contrato ID '%s' no existe"),
    NAME_ALREADY_IN_USE("El nombre '%s' ya está en uso por otro contrato"),
    VALUE_REQUIRED("El valor de entrada no puede estar vacío");

    private final String message;
    public String format(Object... args){
        return String.format(message, args);
    }
}
