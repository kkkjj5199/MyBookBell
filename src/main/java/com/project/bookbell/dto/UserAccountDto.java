package com.project.bookbell.dto;


import com.project.bookbell.domain.UserAccount;
import lombok.Builder;
import lombok.Data;


public record UserAccountDto(

        String email,
        String name
) {

@Builder
public UserAccountDto(UserAccount entity){
        this(entity.getEmail(),entity.getName());
}






}