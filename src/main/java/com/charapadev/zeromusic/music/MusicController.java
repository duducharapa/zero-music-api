package com.charapadev.zeromusic.music;

import com.charapadev.zeromusic.author.Author;
import com.charapadev.zeromusic.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musics")
@AllArgsConstructor
public class MusicController {
    private final MusicService musicService;
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<ShowMusicDTO>> list() {
        List<ShowMusicDTO> musicsFound = musicService.list().stream()
            .map(musicService::convert).toList();

        return ResponseEntity.ok(musicsFound);
    }

    @PostMapping
    public ResponseEntity<ShowMusicDTO> create(@RequestBody CreateMusicDTO createDTO) {
        Author authorFound = authorService.getOne(createDTO.authorID());

        Music createdMusic = musicService.create(createDTO, authorFound);
        ShowMusicDTO showMusic = musicService.convert(createdMusic);

        return ResponseEntity.status(HttpStatus.CREATED).body(showMusic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowMusicDTO> find(@PathVariable("id") Long id) {
        Music musicFound = musicService.getOne(id);
        ShowMusicDTO showMusic = musicService.convert(musicFound);

        return ResponseEntity.ok(showMusic);
    }
}
