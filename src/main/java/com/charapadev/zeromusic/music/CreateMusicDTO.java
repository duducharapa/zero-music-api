package com.charapadev.zeromusic.music;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO containing the acceptable data to add one music", requiredProperties = {"name", "file"})
public record CreateMusicDTO(
    @Schema(description = "Music showable name", example = "Bravely song!!")
    @NotBlank
    String name,

    @Schema(description = "Music cover image as Base64 string data", example = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAUDBAQEAw...")
    String cover,

    @Schema(description = "Music principal playable MP3 file as Base64 string data", example = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAUDBAQEAw...")
    @NotBlank
    String file,

    @Schema(description = "Music owner referenced by ID", example = "3")
    @JsonProperty("author-id") Long authorID
) {
}
