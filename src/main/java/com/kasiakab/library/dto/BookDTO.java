package com.kasiakab.library.dto;

public class BookDTO {
    private Long id;
    private String title;
    private String authorFullName;
    private int availableCopies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public BookDTO(Long id, String title, String authorFullName, int availableCopies) {
        this.id = id;
        this.title = title;
        this.authorFullName = authorFullName;
        this.availableCopies = availableCopies;


    }
}
