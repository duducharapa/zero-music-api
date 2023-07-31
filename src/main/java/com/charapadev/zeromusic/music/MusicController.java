package com.charapadev.zeromusic.music;

import com.charapadev.zeromusic.author.Author;
import com.charapadev.zeromusic.author.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Music", description = "Represents the songs available to listen")
@RestController
@RequestMapping("/musics")
@AllArgsConstructor
public class MusicController {
    private final MusicService musicService;
    private final AuthorService authorService;
    private final MusicMapper musicMapper;

    @Operation(summary = "List playable musics")
    @GetMapping
    public ResponseEntity<List<ShowMusicDTO>> list() {
        List<ShowMusicDTO> musicsFound = musicService.list().stream()
            .map(musicMapper::fromMusicToShow).toList();

        return ResponseEntity.ok(musicsFound);
    }

    @Operation(summary = "Add new music")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ShowMusicDTO> create(@Valid @RequestBody CreateMusicDTO createDTO) {
        Author authorFound = createDTO.authorID() != null ?
            authorService.getOne(createDTO.authorID())  :
            null;

        ShowMusicDTO createdMusic = Optional.of(musicService.create(createDTO, authorFound))
            .map(musicMapper::fromMusicToShow)
            .orElseThrow(RuntimeException::new);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMusic);
    }

    @Operation(summary = "Search existent music")
    @GetMapping("/{id}")
    public ResponseEntity<ShowMusicDTO> find(@PathVariable("id") Long id) {
        ShowMusicDTO musicFound = Optional.of(musicService.getOne(id))
            .map(musicMapper::fromMusicToShow)
            .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(musicFound);
    }
}
