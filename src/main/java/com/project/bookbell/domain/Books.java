package com.project.bookbell.domain;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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


    @Setter
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;
    private String company;
    private String library;
    private Long pages;
    private LocalDate published_date;
    private String ISBN;

    private String coverImageUrl;



    protected Books(){

    }

    private Books(String title, String author, String company, String library, Long pages, LocalDate published_date, String ISBN) {
        this.title = title;
        this.author = author;
        this.company = company;
        this.library = library;
        this.pages = pages;
        this.published_date = published_date;
        this.ISBN = ISBN;
    }

    public static Books of(String title, String author, String company, String library, Long pages, LocalDate published_date, String ISBN) {
        return new Books(title,author,company,library,pages,published_date,ISBN);
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
