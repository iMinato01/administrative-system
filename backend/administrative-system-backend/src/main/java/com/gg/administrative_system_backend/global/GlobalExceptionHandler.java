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
        return ResponseEntity.status(400).body(ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(request.getRequestURI())
                .message("Error en las validaciones")
                .details(errors.values().stream().toList())
                .build());
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleIncompleteUrl(MissingServletRequestParameterException ex, HttpServletRequest request){
        String message = String.format("El parámetro %s de tipo %s es requerido", ex.getParameterName(), ex.getParameterType());
        return ResponseEntity.status(400).body(ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(request.getRequestURI())
                .message(message)
                .build());
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> handleNoHandlerFound(HttpServletRequest request){
        return ResponseEntity.status(404).body(ApiError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .path(request.getRequestURI())
                .message("La URL no existe o fue mal escrita")
                .build());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleMissingBody(HttpMessageNotReadableException ex, HttpServletRequest request){
        return ResponseEntity.status(400).body(ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(request.getRequestURI())
                .message("El cuerpo de la petición es requerido")
                .build());
    }
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEntityAlreadyExists(EntityAlreadyExistsException ex, HttpServletRequest request){
        return ResponseEntity.status(409).body(ApiError.builder()
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .path(request.getRequestURI())
                .message(ex.getMessage())
                .build());
    }
    @ExceptionHandler(PropertyAlreadyInUseException.class)
    public ResponseEntity<ApiError> handlePropertyAlreadyInUse(PropertyAlreadyInUseException ex, HttpServletRequest request){
        return ResponseEntity.status(409).body(ApiError.builder()
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .path(request.getRequestURI())
                .message(ex.getMessage())
                .build());
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(404).body(ApiError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .path(request.getRequestURI())
                .message(ex.getMessage())
                .build());
    }
}
