package com.kasiakab.library.service;

import com.kasiakab.library.dto.BorrowDTO;
import com.kasiakab.library.exception.NotFoundException;
import com.kasiakab.library.model.Book;
import com.kasiakab.library.model.Borrow;
import com.kasiakab.library.model.User;
import com.kasiakab.library.repository.BookRepository;
import com.kasiakab.library.repository.BorrowRepository;
import com.kasiakab.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public BorrowDTO createBorrow(BorrowDTO dto) {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No available copies");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setUser(user);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setBorrowDate(LocalDate.now().plusDays(14));

        Borrow saved = borrowRepository.save(borrow);
        return mapToDTO(saved);
    }

    private BorrowDTO mapToDTO(Borrow borrow) {
        BorrowDTO dto = new BorrowDTO();
        dto.setId(borrow.getId());
        dto.setUserId(borrow.getUser().getId());
        dto.setBookId(borrow.getBook().getId());
        dto.setBorrowDate(borrow.getBorrowDate());
        dto.setReturnDate(borrow.getReturnDate());
        return dto;
    }

    public List<BorrowDTO> getBorrowsByUserId(Long userId) {
        return borrowRepository.findByUserId(userId).stream()
                .map(borrow -> mapToDTO(borrow))
                .collect(Collectors.toList());
    }

    public List<BorrowDTO> getOverdueBorrows() {
        return borrowRepository.findOverdueBorrows().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

}
