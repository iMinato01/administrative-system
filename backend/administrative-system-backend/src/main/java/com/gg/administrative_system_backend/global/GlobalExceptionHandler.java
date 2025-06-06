package com.gg.administrative_system_backend.global;

import com.gg.administrative_system_backend.exception.*;
import com.gg.administrative_system_backend.message.HandlerExceptionMessage;
import com.gg.administrative_system_backend.util.RegexPatterns;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
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
                HandlerExceptionMessage.VALIDATION_EXCEPTION.getMessage(), request.getRequestURI(), errors.values().stream().toList()));
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleIncompleteUrl(MissingServletRequestParameterException ex, HttpServletRequest request){
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HandlerExceptionMessage.REQUEST_PARAMETER_EXCEPTION.format(ex.getParameterName(), ex.getParameterType()), request.getRequestURI()));
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> handleNoHandlerFound(HttpServletRequest request){
        return ResponseEntity.status(404).body(ApiError.of(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                HandlerExceptionMessage.NO_HANDLER_EXCEPTION.getMessage(), request.getRequestURI()));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleMissingBody(HttpServletRequest request){
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HandlerExceptionMessage.MISSING_BODY_EXCEPTION.getMessage(), request.getRequestURI()));
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiError> handleNumberFormat(NumberFormatException ex, HttpServletRequest request){
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HandlerExceptionMessage.NUMBER_FORMAT_EXCEPTION.format(ex.getMessage().replaceAll(RegexPatterns.QUOTED_VALUE, "$1")), request.getRequestURI()));
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
    @ExceptionHandler(ValueRequiredException.class)
    public ResponseEntity<ApiError> handleValueRequired(ValueRequiredException ex, HttpServletRequest request){
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(), request.getRequestURI()));
    }
}
