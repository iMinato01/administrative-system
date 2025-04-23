package com.gg.administrative_system_backend.exception;

public class EntityAlreadyExistsException extends RuntimeException{
    public EntityAlreadyExistsException(String message){
        super(message);
    }
}
