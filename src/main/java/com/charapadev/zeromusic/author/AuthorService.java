package com.charapadev.zeromusic.author;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> list() {
        return authorRepository.findAll();
    }

    public Author create(CreateAuthorDTO createDTO) {
        Author authorToCreate = Author.builder()
            .name(createDTO.name())
            .build();

        authorToCreate = authorRepository.save(authorToCreate);
        return authorToCreate;
    }

    public ShowAuthorDTO convert(Author author) {
        return ShowAuthorDTO.builder()
            .id(author.getId())
            .name(author.getName())
            .build();
    }

    public Author getOne(Long id) throws NoSuchElementException {
        return authorRepository.findById(id)
            .orElseThrow();
    }
}
