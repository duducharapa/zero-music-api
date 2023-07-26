package com.charapadev.zeromusic.global;

import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex) {
        int status = HttpStatus.NOT_FOUND.value();
        ErrorResponse response = new ErrorResponse(status, ex.getMessage());

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleInternal(Exception ex) {
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        ErrorResponse response = new ErrorResponse(status, "Internal server error!");

        return ResponseEntity.status(status).body(response);
    }
}
