package com.kasiakab.library.service;

import com.kasiakab.library.dto.BookDTO;
import com.kasiakab.library.dto.BookRequestDTO;
import com.kasiakab.library.exception.NotFoundException;
import com.kasiakab.library.model.Author;
import com.kasiakab.library.model.Book;
import com.kasiakab.library.model.Category;
import com.kasiakab.library.repository.AuthorRepository;
import com.kasiakab.library.repository.BookRepository;
import com.kasiakab.library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
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

    public Book addBook(BookRequestDTO dto) {
        Optional<Author> authorOpt = authorRepository.findById(dto.getAuthorId());
        if (authorOpt.isEmpty()) {
            throw new RuntimeException("Author not found with id: " + dto.getAuthorId());
        }

        Optional<Category> categoryOpt = categoryRepository.findById(dto.getCategoryId());
        if (categoryOpt.isEmpty()) {
            throw new RuntimeException("Category not found with id: " + dto.getCategoryId());
        }

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(authorOpt.get());
        book.setCategory(categoryOpt.get());
        book.setAvailableCopies(dto.getAvailableCopies());

        return bookRepository.save(book);

    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));
        return BookDTO.fromEntity(book);
    }

    public BookDTO updateBook(Long id, BookRequestDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));

        book.setTitle(dto.getTitle());
        book.setAvailableCopies(dto.getAvailableCopies());

        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found with id: " + dto.getAuthorId()));
        book.setAuthor(author);

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + dto.getCategoryId()));
        book.setCategory(category);

        bookRepository.save(book);
        return BookDTO.fromEntity(book);

    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }


}
