package com.charapadev.zeromusic.author;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Author", description = "Represents the owners of musics")
@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @Operation(summary = "List registered authors")
    @GetMapping
    public ResponseEntity<List<ShowAuthorDTO>> list() {
        List<ShowAuthorDTO> authorsFound = authorService.list().stream()
            .map(authorMapper::fromAuthorToShow).toList();

        return ResponseEntity.ok(authorsFound);
    }

    @Operation(summary = "Register new author")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ShowAuthorDTO> create(@Valid @RequestBody CreateAuthorDTO createDTO) {
        ShowAuthorDTO createdAuthor = Optional.of(authorService.create(createDTO))
            .map(authorMapper::fromAuthorToShow)
            .orElseThrow(RuntimeException::new);
;
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @Operation(summary = "Search existent author")
    @GetMapping("/{id}")
    public ResponseEntity<ShowAuthorDTO> find(@PathVariable("id") Long id) {
        ShowAuthorDTO authorFound = Optional.of(authorService.getOne(id))
            .map(authorMapper::fromAuthorToShow)
            .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(authorFound);
    }
}
