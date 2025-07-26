package com.kasiakab.library.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookRequestDTO {

    @NotBlank
    private String title;

    @NotNull
    private Long authorId;

    @NotNull
    private Long categoryId;

    @Min(1)
    private int availableCopies;


    public BookRequestDTO() {
    }

}
