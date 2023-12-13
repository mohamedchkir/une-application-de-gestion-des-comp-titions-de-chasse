package com.example.aftas.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponses> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatusCode statusCode = ex.getStatusCode();
        String message = ex.getReason();

        ErrorResponses errorResponse = new ErrorResponses(statusCode.value(), message);
        return ResponseEntity.status(statusCode).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponses> handleIllegalArgumentException(IllegalArgumentException ex) {
        int statusCode = HttpStatus.BAD_REQUEST.value();
        String message = ex.getMessage();

        ErrorResponses errorResponse = new ErrorResponses(statusCode, message);
        return ResponseEntity.status(statusCode).body(errorResponse);
    }

    private record ErrorResponses(int statusCode, String message) {
    }

}
