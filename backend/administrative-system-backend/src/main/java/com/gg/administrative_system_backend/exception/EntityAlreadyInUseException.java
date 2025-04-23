package com.gg.administrative_system_backend.exception;

public class EntityAlreadyInUseException extends RuntimeException {
    public EntityAlreadyInUseException(String message){
        super(message);
    }
}
