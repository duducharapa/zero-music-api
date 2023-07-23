package com.charapadev.zeromusic.music;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;

    public List<Music> list() {
        return musicRepository.findAll();
    }

    public Music create(CreateMusicDTO createDTO) {
        Music musicToCreate = Music.builder()
            .name(createDTO.name())
            .build();

        musicToCreate = musicRepository.save(musicToCreate);
        return musicToCreate;
    }

    public Music getOne(Long id) throws NoSuchElementException {
        return musicRepository.findById(id)
            .orElseThrow();
    }

    public ShowMusicDTO convert(Music music) {
        return ShowMusicDTO.builder()
            .id(music.getId())
            .name(music.getName())
            .coverUrl(music.getCoverUrl())
            .fileUrl(music.getFileUrl())
            .build();
    }
}
