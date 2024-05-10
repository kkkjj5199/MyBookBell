package com.project.bookbell.Controller;


import com.project.bookbell.domain.Books;
import com.project.bookbell.domain.Library;
import com.project.bookbell.domain.type.SearchType;
import com.project.bookbell.dto.BooksDto;
import com.project.bookbell.repository.BooksRepository;
import com.project.bookbell.service.BookService;
import com.project.bookbell.service.PaginationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@Slf4j
@RequestMapping("/books")
@RequiredArgsConstructor
//@Import(SecurityConfig.class)
public class BookController {


    @Autowired
    private final BookService bookService;


    private final PaginationService paginationService;



    @GetMapping()
    public String books(
            @RequestParam(required = false) SearchType SearchType,
            @RequestParam(required = false) String SearchValue,
            @PageableDefault(page=0,size = 10,direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {

//        model.addAttribute("searchList",bookService.searchBooks(searchTypes,SearchValue,pageable));



        Page<BooksDto> list = bookService.searchBooks(SearchType,SearchValue,pageable);



        model.addAttribute("books",bookService.getBookList());
        model.addAttribute("list", list);
        model.addAttribute("searchType",SearchType.values());








        return "books/index";
    }






    //단건조회
    @GetMapping("detail/{id}")
    public String book(@PathVariable(name = "id") Long id, ModelMap map){
        map.addAttribute("books",BooksDto.from(bookService.getBook(id)));

        return "books/detail";
    }

    @GetMapping("/library")
    public List library(){
        Long id = 1000L;
        List<Books> books = bookService.getBooks_library(id);
        return books;
    }

    @GetMapping("/getListBook")
    public List<Books> getBookList(){

        return bookService.getBookList();
    }




}
