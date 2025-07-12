package com.kasiakab.library.service;

import com.kasiakab.library.dto.AuthorDTO;
import com.kasiakab.library.model.Author;
import com.kasiakab.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        return authors.stream()
                .map(author -> new AuthorDTO(
                        author.getId(),
                        author.getFirstName(),
                        author.getLastName()
                ))
                .collect(Collectors.toList());

    }
}
