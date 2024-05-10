package com.project.bookbell.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.service.annotation.GetExchange;

@Entity
@ToString
@Table
@Getter
public class bookIN extends BaseEntity {
    @Id
    @Column(name = "IN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "books_number")
    private Books books;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_order_id")
    private Orders orders;




}
