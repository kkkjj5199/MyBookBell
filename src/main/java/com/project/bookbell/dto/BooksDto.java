package com.project.bookbell.dto;

import com.project.bookbell.domain.Books;
import com.project.bookbell.domain.Library;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record BooksDto ( Long id,
                         String title,
                       String author,
                       String ISBN,
                         String status,
                         Library library


                      ) {


    public static  BooksDto of(Long id, String title, String author, String ISBN,

                               String status,
                               Library library) {
            return new BooksDto(id,title, author, ISBN,status, library);
    }




    public static BooksDto from(Books entity){

        return new BooksDto(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getISBN(),
                entity.getCompany(),
                entity.getLibrary()
        );
    }


    public Books toEntity() {

        return Books.of(title,author,ISBN,status,library);
    }
}
