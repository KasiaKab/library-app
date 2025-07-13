package com.kasiakab.library.controller;

import com.kasiakab.library.dto.BookDTO;
import com.kasiakab.library.dto.BookRequestDTO;
import com.kasiakab.library.model.Book;
import com.kasiakab.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookRequestDTO dto) {
        Book createdBook = bookService.addBook(dto);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

}
