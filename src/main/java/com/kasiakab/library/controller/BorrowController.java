package com.kasiakab.library.controller;

import com.kasiakab.library.dto.BorrowDTO;
import com.kasiakab.library.service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BorrowDTO>> getBorrowsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(borrowService.getBorrowsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<BorrowDTO> createBorrow(@Valid @RequestBody BorrowDTO borrowDTO) {
        return ResponseEntity.ok(borrowService.createBorrow(borrowDTO));
    }
}
