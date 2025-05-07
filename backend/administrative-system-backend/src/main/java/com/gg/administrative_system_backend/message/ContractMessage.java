package com.gg.administrative_system_backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContractMessage {
    //  Controller  //
    LIST_ALL("Todos los contratos"),
    LIST_NAMES("Nombres de contratos '%s'"),
    LIST_BY_VALUE("Contratos '%s' filtrados"),
    CREATED_SUCCESS("El contrato '%s' ha sido guardado"),
    UPDATED_SUCCESS("El contrato '%s' ha sido actualizado");
    //  Service  //

    //  CreateDTO //

    //  UpdateDTO  //
    private final String message;
    public String format(Object... args){
        return String.format(message, args);
    }
}
