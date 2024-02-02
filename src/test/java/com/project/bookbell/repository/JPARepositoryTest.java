package com.project.bookbell.repository;

import com.project.bookbell.config.JpaConfig;
import com.project.bookbell.domain.Books;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@DataJpaTest
@Import(JpaConfig.class)
class JPARepositoryTest {

    private final BooksRepository booksRepository;

    public JPARepositoryTest(
            @Autowired  BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @DisplayName("select 테스트")
    @Test
    public void givenTestData_whenSelecting_thenWorksFine() {
        //given

        //when
        List<Books> booksList = booksRepository.findAll();

        //then
        assertThat(booksList)
                .isNotNull();
//                .hasSize(100);

    }

    @Test
    public void givenTestData_whenInserting_thenWorksFine() throws Exception {
        //given
        long previousCount = booksRepository.count();


        //when
        Books books = booksRepository.save(Books.of("사람들이 날 찾았니","양수산","서해문집",(long)250, LocalDate.ofEpochDay(2010-01-01),"9791192988108", "Y"));
        //then

        assertThat(booksRepository.count()).isEqualTo(previousCount+1);

    }

    @Disabled
    @Test
    public void givenTestData_whenUpdating_thenWorksFine() throws Exception {
        //given
        Optional<Books> books = booksRepository.findById(1L);
        System.out.println(books);
        books.ifPresent(selectBook->{
            selectBook.setTitle("제목변경");
        });




        //when


        //then


    }

    @Disabled
    @Test
    public void givenTestData_whenDeleting_thenWorksFine() throws Exception {


        //given
//        Books books = booksRepository.findById(1L).orElseThrow();
//        long preCount = booksRepository.count();

//        Optional<Books> books = booksRepository.findById(2L);
//        System.out.println(books);
//        assertThat(books.isPresent()).isTrue();
//
//        books.ifPresent(selectBook ->{
//            booksRepository.delete(selectBook);
//        });
//
//        Optional<Books> delbooks = booksRepository.findById(2L);
//        assertThat(delbooks.isPresent()).isFalse();

        //when

        //then

    }


}