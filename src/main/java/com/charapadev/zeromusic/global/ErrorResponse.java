package com.charapadev.zeromusic.global;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonSubTypes({
    @JsonSubTypes.Type(value = ErrorResponseImpl.class),
    @JsonSubTypes.Type(value = ValidationErrorResponse.class)
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class ErrorResponse {
    protected Integer code;
    protected LocalDateTime timestamp;
}
