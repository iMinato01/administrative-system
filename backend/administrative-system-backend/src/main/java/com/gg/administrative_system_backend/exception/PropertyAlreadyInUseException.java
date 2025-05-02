package com.gg.administrative_system_backend.exception;

public class PropertyAlreadyInUseException extends RuntimeException {
    public PropertyAlreadyInUseException(String message){
        super(message);
    }
}
