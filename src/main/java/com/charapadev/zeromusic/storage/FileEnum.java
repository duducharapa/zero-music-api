package com.charapadev.zeromusic.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileEnum {
    COVER("cover"),
    MUSIC("music"),
    AUTHOR("author");

    private final String value;
}
