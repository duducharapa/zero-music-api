package com.charapadev.zeromusic.music;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateMusicDTO(
    String name,
    String cover,
    String file,
    @JsonProperty("author-id") Long authorID
) {
}
