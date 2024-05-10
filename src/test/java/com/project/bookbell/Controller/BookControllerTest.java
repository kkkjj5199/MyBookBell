package com.project.bookbell.Controller;



import com.project.bookbell.config.JpaConfig;
import com.project.bookbell.config.SecurityConfig;
import com.project.bookbell.config.TestSecurityConfig;
import com.project.bookbell.domain.Books;
import com.project.bookbell.domain.type.SearchType;
import com.project.bookbell.service.BookService;
import com.project.bookbell.service.PaginationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@DisplayName("View 컨트롤러-책목록 보드")
@Import({TestSecurityConfig.class, JpaConfig.class})
@WebMvcTest(BookController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc
public class BookControllerTest {
    private final MockMvc mvc;

    @MockBean
    private  BookService bookService;
    @MockBean
    private PaginationService paginationService;


    public BookControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }


    @DisplayName("[view][GET] 도서 목록 리스트  페이지 정상호출")
    @Test
    public void givenNothing_whenRequestingBookView_thenReturnView() throws Exception {
        //Given
        given(bookService.searchBooks(eq(SearchType.TITLE),eq(null),any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(),anyInt())).willReturn(List.of(0,1,2,3,4));
        //When
        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("books/index"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attributeExists("paginationBarNumbers"));


        then(bookService).should().searchBooks(eq(null),eq(null),any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(),anyInt());

    }

    @DisplayName("[view][GET]도서 리스트 페이지 - 페이징, 정렬기능")
    @Test
    void givenPaginAndSortingParams_whenSearchingBooksView_thenReturnsBooksView () throws Exception {
        //given
        String sortName = "title";
        String direction = "desc";
        int pageNumber = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(Sort.Order.desc(sortName)));
        List<Integer> barNumbers = List.of(1,2,3,4,5);
        given(bookService.searchBooks(null,null,pageable)).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(pageable.getPageNumber(),Page.empty().getTotalPages())).willReturn(barNumbers);

        //when
        mvc.perform(
                get("/books")
                .queryParam("page",String.valueOf(pageNumber))
                .queryParam("size",String.valueOf(pageSize))
                        .queryParam("sort",sortName + "," + direction)
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("books/index"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("paginationBarNumbers","barNumbers"));

        then(bookService).should().searchBooks(null,null,pageable);
        then(paginationService.getPaginationBarNumbers(pageable.getPageNumber(),Page.empty().getTotalPages()));


        //then

    }

    @Disabled
    @DisplayName("[view][GET] 도서 상세 페이지 정상호출 ")
    @Test
    public void givenNothing_whenRequestingBookDetailView_thenReturnView() throws Exception {
        //Given
        mvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("books/detail"))
                .andExpect(model().attributeExists("books"));

    }

    @DisplayName("[view][GET]도서 검색어와 함께 호출 ")
    @Test
    public void givenKeyword_whenSearchingBookSearchrView_thenReturnView() throws Exception {
        //Given
        SearchType searchType = SearchType.TITLE;
        String searchValue = "title";
        given(bookService.searchBooks(eq(searchType),eq(searchValue),any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(),anyInt())).willReturn(List.of(0,1,2,3,4));


        //When & Then
        mvc.perform(
                        get("/books")
                                .queryParam("searchType", searchType.name())
                                .queryParam("searchValue", searchValue)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("books/search"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attributeExists("searchTypes"));

        then(bookService).should().searchBooks(eq(searchType), eq(searchValue), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());

    }


    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 검색어와 함께 호출")
    @Test
    void givenSearchKeyword_whenSearchingArticlesView_thenReturnsArticlesView() throws Exception {
        // Given
        SearchType searchType = SearchType.TITLE;
        String searchValue = "커피";
        given(bookService.searchBooks(eq(searchType),eq(searchValue),any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4));

        // When & Then
        mvc.perform(
                        get("/books")
                                .queryParam("searchType", searchType.name())
                                .queryParam("searchValue", searchValue)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("books/index"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attributeExists("searchType"));
        then(bookService).should().searchBooks(eq(searchType), eq(searchValue), any(Pageable.class));
//        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }
    @Test
    void noauthgologinpage() throws Exception {
        long bookId = 1L;

        mvc.perform(get("/books/" + bookId))
        .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/**login"));
        then(bookService).shouldHaveNoInteractions();
        then(bookService).shouldHaveNoInteractions();
    }

//    @WithMockUser
//    @Test
//    public void okauthlogin(){
//        Long bookId = 1L;
//        long totalCount = 1L;
//        given(bookService.getBook(bookId)).willReturn(content());
//    }




}




