package com.gg.administrative_system_backend.global;

import com.gg.administrative_system_backend.exception.EntityAlreadyExistsException;
import com.gg.administrative_system_backend.exception.EntityAlreadyInUseException;
import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<String> handleEntityAlreadyExists(){
        return new ResponseEntity<>("La propiedad ya está en uso", HttpStatus.CONFLICT);
    }
    @ExceptionHandler(EntityAlreadyInUseException.class)
    public ResponseEntity<String> handleEntityAlreadyInUse(){
        return new ResponseEntity<>("La entidad ya existe", HttpStatus.CONFLICT);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(){
        return new ResponseEntity<>("La entidad no fue encontrada", HttpStatus.NOT_FOUND);
    }
}
