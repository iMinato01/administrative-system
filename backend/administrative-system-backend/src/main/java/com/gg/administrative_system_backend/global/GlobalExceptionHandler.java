package com.gg.administrative_system_backend.global;

import com.gg.administrative_system_backend.exception.*;
import com.gg.administrative_system_backend.shared.message.HandlerMessage;
import com.gg.administrative_system_backend.response.error.ApiError;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import com.gg.administrative_system_backend.util.RegexPatterns;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

import static com.gg.administrative_system_backend.util.RequestExtractor.ip;
import static com.gg.administrative_system_backend.util.RequestExtractor.user;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request){
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error-> {
            errors.add(error.getField());
        });
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()));
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HandlerMessage.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getMessage(), request.getRequestURI(), errors));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleMissingRequestParameter(MissingServletRequestParameterException exception, HttpServletRequest request){
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()));
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HandlerMessage.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> handleNoHandlerFound(NoHandlerFoundException exception, HttpServletRequest request){
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()));
        return ResponseEntity.status(404).body(ApiError.of(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                HandlerMessage.NO_HANDLER_FOUND_DEXCEPTION.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleMissingBody(HttpMessageNotReadableException exception, HttpServletRequest request){
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()));
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HandlerMessage.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiError> handleNumberFormat(NumberFormatException exception, HttpServletRequest request){
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()));
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HandlerMessage.NUMBER_FORMAT_EXCEPTION.getMessage(), request.getRequestURI(), List.of(exception.getMessage().replaceAll(RegexPatterns.DOUBLE_QUOTED_VALUE, "$1"))));
    }

    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseEntity<ApiError> handleMethodNotFound(NoSuchMethodException exception, HttpServletRequest request){
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()),
                exception.getMessage().replaceAll(RegexPatterns.QUOTED_VALUE, "$2"));
        return ResponseEntity.status(409).body(ApiError.of(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(),
                HandlerMessage.NO_SUCH_METHOD_EXCEPTION.getMessage(), request.getRequestURI(), List.of(exception.getMessage().replaceAll(RegexPatterns.QUOTED_VALUE, "$1"))));
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEntityAlreadyExists(EntityAlreadyExistsException exception, HttpServletRequest request){
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()));
        return ResponseEntity.status(409).body(ApiError.of(HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT.getReasonPhrase(),
                HandlerMessage.ENTITY_ALREADY_EXISTS.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(PropertyAlreadyInUseException.class)
    public ResponseEntity<ApiError> handlePropertyAlreadyInUse(PropertyAlreadyInUseException exception, HttpServletRequest request){
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()));
        return ResponseEntity.status(409).body(ApiError.of(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(),
                HandlerMessage.PROPERTY_ALREADY_IN_USE_EXCEPTION.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException exception, HttpServletRequest request){
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()));
        return ResponseEntity.status(404).body(ApiError.of(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                HandlerMessage.ENTITY_NOT_FOUND.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(ValueRequiredException.class)
    public ResponseEntity<ApiError> handleValueRequired(ValueRequiredException exception, HttpServletRequest request){
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()));
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HandlerMessage.VALUE_REQUIRED_EXCEPTION.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<ApiError> handleReportNotFound(ReportNotFoundException exception, HttpServletRequest request) {
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()));
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HandlerMessage.REPORT_NOT_FOUND_EXCEPTION.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthentication(AuthenticationException exception, HttpServletRequest request){
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()));
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HandlerMessage.AUTHENTICATION_EXCEPTION.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleUnhandledException(Exception exception, HttpServletRequest request){
        log.error(HandlerMessage.LOG.format(user(request), ip(request), exception.getMessage()));
        return ResponseEntity.status(400).body(ApiError.of(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getClass() + GenericMessage.SEPARATOR.getMessage() + exception.getMessage(), request.getRequestURI()));
    }
}
