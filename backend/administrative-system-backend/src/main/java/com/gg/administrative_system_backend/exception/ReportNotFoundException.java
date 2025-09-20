package com.gg.administrative_system_backend.exception;

public class ReportNotFoundException extends RuntimeException{
    public ReportNotFoundException(String message){
        super(message);
    }
}
