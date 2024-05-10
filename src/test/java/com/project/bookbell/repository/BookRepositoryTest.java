package com.project.bookbell.repository;

import com.project.bookbell.domain.Books;
import com.project.bookbell.domain.Library;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
@Disabled
public class BookRepositoryTest {

    @Autowired
    private BooksRepository booksRepository;

  

}
