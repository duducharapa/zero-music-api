package com.charapadev.zeromusic.music;

import com.charapadev.zeromusic.author.ShowAuthorDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ShowMusicDTO(
    @JsonProperty("id") Long id,
    @JsonProperty("name") String name,
    @JsonProperty("cover-url") String coverUrl,
    @JsonProperty("file-url") String fileUrl,
    @JsonProperty("author") ShowAuthorDTO author
    ) {
}
