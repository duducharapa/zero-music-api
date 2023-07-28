package com.charapadev.zeromusic.author;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @GetMapping
    public ResponseEntity<List<ShowAuthorDTO>> list() {
        List<ShowAuthorDTO> authorsFound = authorService.list().stream()
            .map(authorMapper::fromAuthorToShow).toList();

        return ResponseEntity.ok(authorsFound);
    }

    @PostMapping
    public ResponseEntity<ShowAuthorDTO> create(@RequestBody CreateAuthorDTO createDTO) {
        ShowAuthorDTO createdAuthor = Optional.of(authorService.create(createDTO))
            .map(authorMapper::fromAuthorToShow)
            .orElseThrow(RuntimeException::new);
;
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowAuthorDTO> find(@PathVariable("id") Long id) {
        ShowAuthorDTO authorFound = Optional.of(authorService.getOne(id))
            .map(authorMapper::fromAuthorToShow)
            .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(authorFound);
    }
}
