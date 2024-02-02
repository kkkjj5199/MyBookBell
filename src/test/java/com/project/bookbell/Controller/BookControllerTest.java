package com.project.bookbell.Controller;



import com.project.bookbell.config.SecurityConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("View 컨트롤러-책목록 보드")
@WebMvcTest(BookController.class)
//@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    private final MockMvc mvc;

    public BookControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }


    @DisplayName("[view][GET] 주문 리스트  페이지 정상호출")
    @Test
    @WithMockUser(username = "testguest",roles = {"USER"})
    public void givenNothing_whenRequestingBookView_thenReturnView() throws Exception {
        //Given
        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("books/index"))
                .andExpect(model().attributeExists("books"));

    }

    @Disabled
    @DisplayName("[view][GET] 주문 상세 페이지 정상호출 ")
    @Test
    public void givenNothing_whenRequestingBookDetailView_thenReturnView() throws Exception {
        //Given
        mvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("books/detail"))
                .andExpect(model().attributeExists("books"));

    }

    @DisplayName("[view][GET] 주문 검색 페이지 ")
    @Test
    public void givenNothing_whenRequestingBookSearchrView_thenReturnView() throws Exception {
        //Given
        mvc.perform(get("/books/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("books/search"))
                .andExpect(model().attributeExists("books"));

    }


}




