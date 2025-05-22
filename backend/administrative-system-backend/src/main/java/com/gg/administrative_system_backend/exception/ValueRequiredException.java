package com.gg.administrative_system_backend.exception;

public class ValueRequiredException extends RuntimeException{
    public ValueRequiredException(String message){
        super(message);
    }
}
