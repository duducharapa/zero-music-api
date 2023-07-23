package com.charapadev.zeromusic.music;

import com.charapadev.zeromusic.author.Author;
import com.charapadev.zeromusic.author.AuthorService;
import com.charapadev.zeromusic.author.ShowAuthorDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;
    private final AuthorService authorService;

    public List<Music> list() {
        return musicRepository.findAll();
    }

    public Music create(CreateMusicDTO createDTO, Author author) {
        Music musicToCreate = Music.builder()
            .name(createDTO.name())
            .author(author)
            .build();

        musicToCreate = musicRepository.save(musicToCreate);
        return musicToCreate;
    }

    public Music getOne(Long id) throws NoSuchElementException {
        return musicRepository.findById(id)
            .orElseThrow();
    }

    public ShowMusicDTO convert(Music music) {
        ShowAuthorDTO author = authorService.convert(music.getAuthor());

        return ShowMusicDTO.builder()
            .id(music.getId())
            .name(music.getName())
            .coverUrl(music.getCoverUrl())
            .fileUrl(music.getFileUrl())
            .author(author)
            .build();
    }
}
