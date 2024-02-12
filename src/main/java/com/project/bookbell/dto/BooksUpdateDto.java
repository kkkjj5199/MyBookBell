package com.project.bookbell.dto;

import java.io.Serializable;

public record BooksUpdateDto(
        String title,
        String author,
        String ISBN
)  {
    public static BooksUpdateDto of(String title, String author, String ISBN) {
        return  new
                BooksUpdateDto(title,author,ISBN);

    }
}
