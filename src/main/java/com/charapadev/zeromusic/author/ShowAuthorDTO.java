package com.charapadev.zeromusic.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "DTO containing the public data about an author", requiredProperties = {"id", "name"})
@Builder
public record ShowAuthorDTO(
    @Schema(description = "Author identifier", example = "5") @JsonProperty("id")
    Long id,

    @Schema(description = "Author showable name", example = "Aikatsu Stars") @JsonProperty("name")
    String name
) {
}
