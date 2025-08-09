package com.kasiakab.library.controller;

import com.kasiakab.library.dto.AuthorDTO;
import com.kasiakab.library.model.Author;
import com.kasiakab.library.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(
            @PathVariable Long id,
            @RequestBody @Valid AuthorDTO dto) {

        Author updated = authorService.updateAuthor(id, dto);
        return ResponseEntity.ok(updated);
    }

}
