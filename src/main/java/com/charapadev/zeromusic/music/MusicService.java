package com.charapadev.zeromusic.music;

import com.charapadev.zeromusic.author.Author;
import com.charapadev.zeromusic.author.AuthorService;
import com.charapadev.zeromusic.author.ShowAuthorDTO;
import com.charapadev.zeromusic.messages.Message;
import com.charapadev.zeromusic.messages.MessageService;
import com.charapadev.zeromusic.storage.FileEnum;
import com.charapadev.zeromusic.storage.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;
    private final AuthorService authorService;
    private final StorageService storageService;
    private final MessageService messageService;

    public List<Music> list() {
        return musicRepository.findAll();
    }

    private Music save(Music music) {
        return musicRepository.save(music);
    }

    public Music create(CreateMusicDTO createDTO, Author author) {
        Music musicToCreate = Music.builder()
            .name(createDTO.name())
            .author(author)
            .build();

        musicToCreate = save(musicToCreate);

        // Uploading cover image and music file
        Optional.ofNullable(uploadCover(createDTO.cover(), musicToCreate.getId()))
            .ifPresent(musicToCreate::setCoverUrl);
        Optional.ofNullable(uploadMusic(createDTO.file(), musicToCreate.getId()))
            .ifPresent(musicToCreate::setFileUrl);

        musicToCreate = save(musicToCreate);
        return musicToCreate;
    }

    private String uploadCover(String base64data, Long musicID) {
        return uploadFile(base64data, musicID, FileEnum.COVER);
    }

    private String uploadMusic(String base64data, Long musicID) {
        return uploadFile(base64data, musicID, FileEnum.MUSIC);
    }

    private String uploadFile(String base64data, Long musicID, FileEnum fileType) {
        try {
            if (Objects.requireNonNull(base64data).isBlank()) return null;

            return storageService.uploadFile(base64data, musicID, fileType);
        } catch (Exception ex) {
            return null;
        }
    }

    public Music getOne(Long id) {
        return musicRepository.findById(id)
            .orElseThrow(
                () -> new NoSuchElementException(messageService.resolve(Message.MUSIC_NOT_FOUND_BY_ID))
            );
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
