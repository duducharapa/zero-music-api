package com.charapadev.zeromusic.author;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<ShowAuthorDTO>> list() {
        List<ShowAuthorDTO> authorsFound = authorService.list().stream()
            .map(authorService::convert).toList();

        return ResponseEntity.ok(authorsFound);
    }

    @PostMapping
    public ResponseEntity<ShowAuthorDTO> create(@RequestBody CreateAuthorDTO createDTO) {
        Author createdAuthor = authorService.create(createDTO);
        ShowAuthorDTO showAuthor = authorService.convert(createdAuthor);

        return ResponseEntity.status(HttpStatus.CREATED).body(showAuthor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowAuthorDTO> find(@PathVariable("id") Long id) {
        Author authorFound = authorService.getOne(id);
        ShowAuthorDTO showAuthor = authorService.convert(authorFound);

        return ResponseEntity.ok(showAuthor);
    }
}
