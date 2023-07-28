package com.charapadev.zeromusic.author;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO containing the data to adds one author", requiredProperties = {"name"})
public record CreateAuthorDTO(
    @Schema(description = "Author's name to be shown on application", example = "Coda")
    @NotBlank
    String name
) {
}
