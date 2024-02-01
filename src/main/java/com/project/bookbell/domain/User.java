package com.project.bookbell.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@Table
@ToString
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false)
    private String address;

    private String email;

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
