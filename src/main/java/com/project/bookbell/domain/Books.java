package com.project.bookbell.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "author"),
        @Index(columnList = "number")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Books extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number", nullable = false)
    private Long id;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;
    private String company;

    private Long pages;
    private LocalDate published_date;
    private String ISBN;

    private String coverImageUrl;

    private String status;

    private Long stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIBRARY_ID")
    private Library library;



    protected Books(String title, String author, String isbn, String status, Library library){

    }

    @Builder
    public Books(String title, String author, String company, Long pages, LocalDate published_date, String ISBN, String status, Library library) {
        this.title = title;
        this.author = author;
        this.company = company;
        this.pages = pages;
        this.published_date = published_date;
        this.ISBN = ISBN;
        this.status = status;
        this.library = library;

    }

    public Books() {

    }


    public static Books of(String title, String author, String isbn, String status, Library library) {
        return new Books(title,author,isbn,status,library);

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Books books)) return false;
        return id !=null && id.equals(books.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
