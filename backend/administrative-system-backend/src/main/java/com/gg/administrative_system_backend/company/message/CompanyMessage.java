package com.gg.administrative_system_backend.company.message;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CompanyMessage {
    COMPANY_NOT_FOUND("La compañia con ID '%s' no existe"),
    NAME_ALREADY_IN_USE("El nombre '%s' ya está en uso por otro proveedor"),
    RFC_ALREADY_IN_USE("El RFC '%s' ya está en uso por otro proveedor");
    private final String message;
    public String format(Object... args){
        return String.format(message, args);
    }
}