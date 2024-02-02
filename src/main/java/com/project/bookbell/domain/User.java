package com.project.bookbell.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table
@ToString
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false)
    private String address;

    private String email;

    @OneToMany
    @JoinColumn(name = "USER_ID")
    private List<Orders> ordersList = new ArrayList<>();

    protected User() {
    }

    private User(String name, String phone_number, String address, String email) {
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.email = email;
    }


    public static  User of(String name, String phone_number, String address, String email) {
        return new User(name,phone_number,address,email);
    }


}
