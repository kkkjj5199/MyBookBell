package com.project.bookbell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BookbellApplication {

    public static void main(String[] args) {

        SpringApplication.run(BookbellApplication.class, args);

    }

}
