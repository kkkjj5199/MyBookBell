package com.project.bookbell.service;

import com.project.bookbell.domain.Books;
import com.project.bookbell.domain.type.SearchType;
import com.project.bookbell.dto.BooksDto;
import com.project.bookbell.dto.BooksUpdateDto;
import com.project.bookbell.repository.BooksRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

@DisplayName("비즈니스 로직 - 책목록")
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BooksRepository booksRepository;

//    @DisplayName("게시글 검색하면,")
//    @Test
//    public  void givenSearchWhenBooksthenList() throws Exception {
//        //given
//
//
//        //when
//        Page<BooksDto> books =  bookService.searchBooks(SearchType.TITLE,"title"); // 책제목,작가명,ISBN,도서번호
//
//        //then
//        assertThat(books).isNotNull();
//
//    }

    @Test
    public void givenDetailBookWhenSearchThenDetail() throws Exception {
        //given

        //when
        List<BooksDto> book = bookService.searchBook(1L);

        //then
        assertThat(book).isNotNull();

    }

    @DisplayName("도서 정보 생성.")
    @Test
    public void givenBookInfo_whenSavingBook_thenSavingBook() throws Exception {
        //given

        BooksDto dto = BooksDto.of(100L,"주말만 기다리지 않는 삶을 위해 평일도 인생이니까","김신지","12345465-A","알에이치코리아",LocalDateTime.now());
        BDDMockito.given(booksRepository.save(any(Books.class))).willReturn(any(Books.class));

        //when
        bookService.saveBook(dto);
        //then
        then(booksRepository).should().save(any(Books.class));



    }

    @DisplayName("도서 정보 수정.")
    @Test
    public void givenBookInfo_whenUpdateBook_thenUpdateBook() throws Exception {
        //given


        BDDMockito.given(booksRepository.save(any(Books.class))).willReturn(null);


        //when
        bookService.updateBooks(1L,BooksUpdateDto.of("title","author","ISBN"));
        //then
        then(booksRepository).should().save(any(Books.class));



    }

    @Test
    public void givenBookInfo_whenDeleteBook_thendeleteBook() throws Exception {
        //given
        willDoNothing().given(booksRepository).delete(any(Books.class));


        //when
        bookService.deleteBooks(1L);
        //then
        then(booksRepository).should().save(any(Books.class));


    }






}