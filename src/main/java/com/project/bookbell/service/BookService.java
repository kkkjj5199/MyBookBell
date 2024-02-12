package com.project.bookbell.service;

import com.project.bookbell.dto.BooksDto;
import com.project.bookbell.dto.BooksUpdateDto;
import com.project.bookbell.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BookService {

    private final BooksRepository booksRepository;


//    @Transactional(readOnly = true)
//    public Page<BooksDto> searchBooks(SearchType searchType, String searchKeywards) {
//        if (searchKeywards)
//        return Page.empty();
//    }

    public List<BooksDto> searchBook(long l) {
        return null;
    }

    public void saveBook(BooksDto dto) {

    }

    public void updateBooks (long l, BooksUpdateDto dto){

    }

    public void deleteBooks(long l) {
    }
}
