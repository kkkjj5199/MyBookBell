package com.project.bookbell.domain;

import com.project.bookbell.dto.UserAccountDto;
import com.project.bookbell.dto.security.KaKaoOAuth2Response;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table
@ToString
public class UserAccount extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String address;

    private String email;

    private String name;


    public void registUser( String email, String name){

        this.email = email;
        this.name = name;
    }



    public UserAccount() {
    }



}
