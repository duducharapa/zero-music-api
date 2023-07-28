package com.charapadev.zeromusic.author;

import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public ShowAuthorDTO fromAuthorToShow(Author author) {
        return ShowAuthorDTO.builder()
            .id(author.getId())
            .name(author.getName())
            .build();
    }
}
