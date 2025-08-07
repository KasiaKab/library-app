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

    @GetMapping("/available")
    public List<BookDTO> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookRequestDTO dto) {
        Book createdBook = bookService.addBook(dto);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDTO dto) {
        return bookService.updateBook(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

}
