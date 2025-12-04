package com.gg.administrative_system_backend.shared.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HandlerMessage {
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION("Error en las validaciones"),
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("El parámetro en la petición es requerido"),
    NO_HANDLER_FOUND_DEXCEPTION("La URL ingresada no existe"),
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("El cuerpo de la petición no cumple con el formato correspondiente"),
    NUMBER_FORMAT_EXCEPTION("No se pudo convertir el valor ingresado"),
    NO_SUCH_METHOD_EXCEPTION("El método o propiedad no están correctamente manejados"),
    ENTITY_ALREADY_EXISTS_EXCEPTION("La entidad ya existe"),
    PROPERTY_ALREADY_IN_USE_EXCEPTION("El valor ingresado ya está siendo usado por otra entidad"),
    ENTITY_NOT_FOUND_EXCEPTION("No se encontró la entidad buscada"),
    VALUE_REQUIRED_EXCEPTION("El valor es requerido"),
    REPORT_NOT_FOUND_EXCEPTION("No se econtró la plantilla del reporte"),
    AUTHENTICATION_EXCEPTION("Error con la autenticación"),
    ENTITY_ALREADY_EXISTS("La entidad ya existe"),
    ENTITY_NOT_FOUND("No se encontró la entidad con el valor ingresado"),
    VALUE_REQUIRED("El valor de entrada no puede estar vacío"),
    COMPILE_FAIL("Error al compilar el recurso"),
    REPORT_NOT_FOUND("No se encontró el reporte"),
    EXCEPTION("Ocurrió un error inesperado"),
    LOG("User: %s, IP: %s, Message: %s");
    private final String message;
    public String format(Object... args){
        return String.format(message, args);
    }
}

