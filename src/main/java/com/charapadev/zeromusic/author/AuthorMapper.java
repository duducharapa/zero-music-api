package com.charapadev.zeromusic.author;

import com.charapadev.zeromusic.music.Music;
import com.charapadev.zeromusic.music.ShowMusicDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public ShowAuthorDTO fromAuthorToShow(Author author) {
        return ShowAuthorDTO.builder()
            .id(author.getId())
            .name(author.getName())
            .build();
    }

    public ShowAuthorDTO getUnknownAuthor() {
        return ShowAuthorDTO.builder()
            .id(0L)
            .name("Unknown")
            .build();
    }
}
