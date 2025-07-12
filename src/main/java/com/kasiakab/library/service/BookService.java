package com.kasiakab.library.service;

import com.kasiakab.library.dto.BookDTO;
import com.kasiakab.library.model.Book;
import com.kasiakab.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName(),
                        book.getAvailableCopies()
                ))
                .collect(Collectors.toList());
    }

}
