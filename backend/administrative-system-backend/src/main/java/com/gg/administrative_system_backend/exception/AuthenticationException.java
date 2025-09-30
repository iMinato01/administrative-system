package com.gg.administrative_system_backend.exception;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message){
        super(message);
    }
}
