package com.project.bookbell;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
@ConfigurationPropertiesScan
public class BookbellApplication {

    public static void main(String[] args) {

        SpringApplication.run(BookbellApplication.class, args);



    }



}
