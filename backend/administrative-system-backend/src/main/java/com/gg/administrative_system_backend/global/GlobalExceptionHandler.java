package com.gg.administrative_system_backend.global;

import com.gg.administrative_system_backend.exception.ApiError;
import com.gg.administrative_system_backend.exception.EntityAlreadyExistsException;
import com.gg.administrative_system_backend.exception.PropertyAlreadyInUseException;
import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error-> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Error en las validaciones", request.getRequestURI(), errors.values().stream().toList()));
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleIncompleteUrl(MissingServletRequestParameterException ex, HttpServletRequest request){
        String message = String.format("El parámetro %s de tipo %s es requerido", ex.getParameterName(), ex.getParameterType());
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                message, request.getRequestURI()));
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> handleNoHandlerFound(HttpServletRequest request){
        return ResponseEntity.status(404).body(ApiError.of(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                "La URL no existe o fue mal escrite", request.getRequestURI()));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleMissingBody(HttpMessageNotReadableException ex, HttpServletRequest request){
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "El cuerpo de la petición es requerido", request.getRequestURI()));
    }
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEntityAlreadyExists(EntityAlreadyExistsException ex, HttpServletRequest request){
        return ResponseEntity.status(409).body(ApiError.of(HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(), request.getRequestURI()));
    }
    @ExceptionHandler(PropertyAlreadyInUseException.class)
    public ResponseEntity<ApiError> handlePropertyAlreadyInUse(PropertyAlreadyInUseException ex, HttpServletRequest request){
        return ResponseEntity.status(409).body(ApiError.of(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(), request.getRequestURI()));
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(404).body(ApiError.of(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(), request.getRequestURI()));
    }
}
