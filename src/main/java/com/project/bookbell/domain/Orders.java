package com.project.bookbell.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Entity
@Table
public class Orders extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false)
    private Long id;

    private LocalDate inDate;

    private LocalDate outDate;

    private String status;

    private Long total;

    @OneToMany
    @JoinColumn(name = "ORDER_ID") //books 테이블의 number FK
    private List<Books> books = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID",insertable = false,updatable = false)
    private User user;


}
