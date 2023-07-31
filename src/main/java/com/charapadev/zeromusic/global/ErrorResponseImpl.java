package com.charapadev.zeromusic.global;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponseImpl extends ErrorResponse {
    private final String message;

    public ErrorResponseImpl(Integer code, String message, LocalDateTime timestamp) {
        super(code, timestamp);
        this.message = message;
    }
}
