package com.charapadev.zeromusic.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Message {
    AUTHOR_NOT_FOUND_BY_ID("author.not-found.id"),
    MUSIC_NOT_FOUND_BY_ID("music.not-found.id");

    private final String property;
}
