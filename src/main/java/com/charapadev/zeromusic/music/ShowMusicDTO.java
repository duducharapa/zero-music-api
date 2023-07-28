package com.charapadev.zeromusic.music;

import com.charapadev.zeromusic.author.ShowAuthorDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "DTO containing the public data about a music", requiredProperties = {"id", "name", "fileUrl", "author"})
@Builder
public record ShowMusicDTO(
    @Schema(description = "Music identifier", example = "2") @JsonProperty("id")
    Long id,

    @JsonProperty("name") String name,

    @Schema(description = "URL of music cover file with JPEG extension", example = "http://localhost:9000/filetest.jpeg")
    @JsonProperty("cover-url") String coverUrl,

    @Schema(description = "URL of music playable MP3 file", example = "http://localhost:9000/music.mp3")
    @JsonProperty("file-url") String fileUrl,

    @Schema(description = "Music author public data")
    @JsonProperty("author") ShowAuthorDTO author
    ) {
}
