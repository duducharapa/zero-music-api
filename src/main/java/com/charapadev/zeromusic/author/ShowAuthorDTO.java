package com.charapadev.zeromusic.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ShowAuthorDTO(
    @JsonProperty("id") Long id,
    @JsonProperty("name") String name
) {
}
