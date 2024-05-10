package com.project.bookbell.service;

import com.project.bookbell.domain.Books;
import com.project.bookbell.domain.type.SearchType;
import com.project.bookbell.dto.BooksDto;
import com.project.bookbell.repository.BooksRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.project.bookbell.domain.type.SearchType.AUTHOR;
import static com.project.bookbell.domain.type.SearchType.TITLE;

@RequiredArgsConstructor
@Transactional
@Service
public class BookService {




    private final BooksRepository booksRepository;


    @Transactional(readOnly = true)
    public List<Books> getBookList(){
        return booksRepository.findAll();
    }



    @Transactional(readOnly = true)
    public Page<BooksDto> searchBooks(SearchType SearchType, String searchKeywards, Pageable pageable) {
        if (searchKeywards == null || searchKeywards.isBlank()){
            return null;
        }

        return switch (SearchType) {
            case TITLE-> booksRepository.findByTitleContaining(searchKeywards, pageable);
            case AUTHOR -> booksRepository.findByAuthorContaining(searchKeywards, pageable);



        };

    }

    //도서 단건 조회.
    @Transactional(readOnly = true)
    public Books getBook(Long BookId){

        return booksRepository.findById(BookId)

                .orElseThrow(()-> new EntityNotFoundException("없는 도서 입니다 - bookId: " + BookId));
    }

    @Transactional(readOnly = true)
    public List<Books> getBooks_library(Long libraryId){

        return booksRepository.findByLibraryId(1000L);


    }

    public List<BooksDto> searchBook(long l) {
        return null;
    }

    public void saveBook(BooksDto dto) {
        booksRepository.save(dto.toEntity());

    }

    public void updateBooks (BooksDto dto){
        Books books = booksRepository.getReferenceById(dto.id());






    }

    public void deleteBooks(long bookId) {
        booksRepository.deleteById(bookId);
    }


}
