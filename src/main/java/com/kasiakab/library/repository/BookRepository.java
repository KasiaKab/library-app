package com.kasiakab.library.repository;

import com.kasiakab.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.availableCopies > 0")
    List<Book> findAvailableCopies();
}
