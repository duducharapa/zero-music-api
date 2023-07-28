package com.charapadev.zeromusic.music;

import com.charapadev.zeromusic.author.AuthorMapper;
import com.charapadev.zeromusic.author.ShowAuthorDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MusicMapper {
    private final AuthorMapper authorMapper;

    public ShowMusicDTO fromMusicToShow(Music music) {
        
        ShowAuthorDTO author = authorMapper.fromAuthorToShow(music.getAuthor());

        return ShowMusicDTO.builder()
            .id(music.getId())
            .name(music.getName())
            .fileUrl(music.getFileUrl())
            .coverUrl(music.getCoverUrl())
            .author(author)
            .build();
    }
}
