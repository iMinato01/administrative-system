package com.gg.administrative_system_backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SupplierMessage {
    // CONTROLLER //
    NAME_ALREADY_IN_USE("El nombre '%s' ya está en uso por otro proveedor"),
    RFC_ALREADY_IN_USE("El RFC '%s' ya está en uso por otro proveedor"),
    // SERVICE //
    SUPPLIER_NOT_FOUND("El contrato ID %s no existe"),
    VALUE_REQUIRED("El valor de entrada no puede estar vacío");

    private final String message;
    public String format(Object... args){
        return String.format(message, args);
    }
}
