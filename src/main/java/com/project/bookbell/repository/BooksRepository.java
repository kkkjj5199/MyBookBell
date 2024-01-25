package com.project.bookbell.repository;

import com.project.bookbell.domain.Books;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BooksRepository extends JpaRepository<Books,Long> {


}
