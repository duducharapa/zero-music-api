package com.charapadev.zeromusic.music;

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

    @GetMapping
    public ResponseEntity<List<ShowMusicDTO>> list() {
        List<ShowMusicDTO> musicsFound = musicService.list().stream()
            .map(musicService::convert).toList();

        return ResponseEntity.ok(musicsFound);
    }

    @PostMapping
    public ResponseEntity<ShowMusicDTO> create(@RequestBody CreateMusicDTO createDTO) {
        Music createdMusic = musicService.create(createDTO);
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
