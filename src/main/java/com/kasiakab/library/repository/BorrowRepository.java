package com.kasiakab.library.repository;

import com.kasiakab.library.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByUserId(Long userId);

    @Query("SELECT b FROM Borrow b WHERE b.returnDate < CURRENT_DATE")
    List<Borrow> findOverdueBorrows();

}
