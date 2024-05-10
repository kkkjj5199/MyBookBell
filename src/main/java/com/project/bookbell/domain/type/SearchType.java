package com.project.bookbell.domain.type;

import lombok.Getter;
import org.springframework.web.service.annotation.GetExchange;

public enum SearchType {
    TITLE("TITLE"),
    AUTHOR("AUTHOR");


    @Getter
    private final String description;



    SearchType(String description ){
        this.description =description;

    }


}
