package com.kasiakab.library.dto;

import com.kasiakab.library.model.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String title;
    private String authorFullName;
    private int availableCopies;

    public BookDTO(Long id, String title, String authorFullName, int availableCopies) {
        this.id = id;
        this.title = title;
        this.authorFullName = authorFullName;
        this.availableCopies = availableCopies;
    }

    public static BookDTO fromEntity(Book book) {
        String authorFullName = "";
        if (book.getAuthor() != null) {
            authorFullName = book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName();
        }

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                authorFullName,
                book.getAvailableCopies()
        );

    }
}
