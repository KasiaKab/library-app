package com.kasiakab.library.config;

import com.kasiakab.library.model.Author;
import com.kasiakab.library.model.Book;
import com.kasiakab.library.model.Category;
import com.kasiakab.library.repository.AuthorRepository;
import com.kasiakab.library.repository.BookRepository;
import com.kasiakab.library.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(AuthorRepository authorRepo, BookRepository bookRepo, CategoryRepository categoryRepo) {
        return args -> {
            Author brown = new Author();
            brown.setFirstName("Dan");
            brown.setLastName("Brown");
            authorRepo.save(brown);

            Category thriler = new Category();
            thriler.setName("Thriller");
            categoryRepo.save(thriler);

            Book book1 = new Book();
            book1.setTitle("Inferno");
            book1.setPublicationYear(2013);
            book1.setAvailableCopies(2);
            book1.setAuthor(brown);
            bookRepo.save(book1);

            System.out.println("Sample data inserted");
        };
    }

}
