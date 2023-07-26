package com.charapadev.zeromusic.global;

public record ErrorResponse(
    Integer code,
    String message
) {
}
