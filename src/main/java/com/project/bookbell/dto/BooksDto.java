package com.project.bookbell.dto;

import com.project.bookbell.domain.Books;

import java.io.Serializable;
import java.time.LocalDateTime;

public record BooksDto ( Long id,
                         String title,
                       String author,
                       String ISBN,
                       String company,
                         LocalDateTime create_date
                      ) implements Serializable {


    public static  BooksDto of(Long id, String title, String author, String ISBN, String company, LocalDateTime create_date) {
            return new BooksDto(id,title, author, ISBN,company,create_date);
    }



    public static BooksDto from(Books entity){

        return new BooksDto(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getISBN(),
                entity.getCompany(),
                entity.getCreate_date()
        );
    }



}
