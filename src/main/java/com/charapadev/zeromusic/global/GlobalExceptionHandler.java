package com.charapadev.zeromusic.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex) {
        int status = HttpStatus.NOT_FOUND.value();
        var response = new ErrorResponseImpl(status, ex.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleBadRequests(MethodArgumentNotValidException ex) {
        int status = HttpStatus.BAD_REQUEST.value();
        var errorField = ex.getFieldErrors().get(0);
        var response = new ValidationErrorResponse(
            status, errorField.getField(), errorField.getDefaultMessage(), LocalDateTime.now()
        );

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleInternal(Exception ex) {
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        var response = new ErrorResponseImpl(status, "Internal server error!", LocalDateTime.now());

        return ResponseEntity.status(status).body(response);
    }
}
