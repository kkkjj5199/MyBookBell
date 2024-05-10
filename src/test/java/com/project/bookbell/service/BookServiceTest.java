package com.project.bookbell.service;

import com.project.bookbell.domain.Books;
import com.project.bookbell.domain.Library;
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
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.config.http.MatcherType.mvc;

@DisplayName("비즈니스 로직 - 책목록")
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BooksRepository booksRepository;
    private com.project.bookbell.domain.Library Library;

    @DisplayName("검색어 없이 도서 검색하면 페이지 반환")
    @Test
    public  void givenSearchWhenBooksthenList() throws Exception {
        //given
        Pageable pageable = Pageable.ofSize(20);

//        given(booksRepository.findAll(pageable)).willReturn(Page.empty());

        // When
//        Page<BooksDto> books = bookService.searchBook(null,searchType)

        // Then
//        assertThat(books).isEmpty();
        then(booksRepository).should().findAll(pageable);
    }

    @DisplayName("단건조회-도서")
    @Test
    public void givenBookId_whenSearchingBook_thenReturnsBook() throws Exception {
        //given
        Long bookId = 20L;
        Books books = createBook(bookId);
      

        BDDMockito.given(booksRepository.findById(bookId)).willReturn(Optional.of(books));
        //when
        BooksDto dto = BooksDto.from(bookService.getBook(bookId));
        //then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("title",books.getTitle());
    }




    @DisplayName("도서 정보 수정.")
    @Test
    public void givenBookInfo_whenUpdateBook_thenUpdateBook() throws Exception {
        //given
        Books books = createBook();
        BooksDto dto = createBookDto("새 제목", "새 내용");
        given(booksRepository.getReferenceById(dto.id())).willReturn(books);




//        BDDMockito.given(booksRepository.getReferenceById(dto.id()any(Books.class))).willReturn(null);


        //when
//        bookService.updateBooks(BooksUpdateDto.of("title","author","ISBN"));
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

    private Books createBook(){
        return createBook(1L);
    }

    private Books createBook(Long id){
        Books books = Books.of(
                "title",
                "김유진",
                "열린책들",
                "Y",

                Library

        );
        ReflectionTestUtils.setField(books,"id",id);
        return books;
    }

    private BooksDto createBookDto(String title, String author) {
        return BooksDto.of(
                2L,
                title,
                author,
                null,
                null,
                Library
        );


    }






}