package com.charapadev.zeromusic.global;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ValidationErrorResponse extends ErrorResponse {
    private final String field;
    private final String message;

    public ValidationErrorResponse(Integer code, String field, String message, LocalDateTime timestamp) {
        super(code, timestamp);
        this.field = field;
        this.message = message;
    }
}
