package com.charapadev.zeromusic.music;

import com.charapadev.zeromusic.author.Author;
import com.charapadev.zeromusic.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musics")
@AllArgsConstructor
public class MusicController {
    private final MusicService musicService;
    private final AuthorService authorService;
    private final MusicMapper musicMapper;

    @GetMapping
    public ResponseEntity<List<ShowMusicDTO>> list() {
        List<ShowMusicDTO> musicsFound = musicService.list().stream()
            .map(musicMapper::fromMusicToShow).toList();

        return ResponseEntity.ok(musicsFound);
    }

    @PostMapping
    public ResponseEntity<ShowMusicDTO> create(@RequestBody CreateMusicDTO createDTO) {
        Author authorFound = authorService.getOne(createDTO.authorID());
        ShowMusicDTO createdMusic = Optional.of(musicService.create(createDTO, authorFound))
            .map(musicMapper::fromMusicToShow)
            .orElseThrow(RuntimeException::new);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMusic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowMusicDTO> find(@PathVariable("id") Long id) {
        ShowMusicDTO musicFound = Optional.of(musicService.getOne(id))
            .map(musicMapper::fromMusicToShow)
            .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(musicFound);
    }
}
