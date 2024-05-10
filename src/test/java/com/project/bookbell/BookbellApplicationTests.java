package com.project.bookbell;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ConfigurationPropertiesScan
@Transactional
class BookbellApplicationTests {


    @PersistenceContext
    EntityManager em;

    @Test
    void contextLoads() {

    }


//    @Bean
//    JPAQueryFactory jpaQueryFactory(EntityManager em){
//        return new JPAQueryFactory(em);
//    }

}
