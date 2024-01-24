package com.project.bookbell.domain;

import java.time.LocalDateTime;

public class Books extends BaseEntity{

    private Long numer;
    private String title;
    private String author;
    private String company;
    private String libraryName;
    private Long pages;
    private LocalDateTime published_date;
    private String ISBN;
}
