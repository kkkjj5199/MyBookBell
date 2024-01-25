package com.project.bookbell.repository;

import com.project.bookbell.domain.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface BooksRepository extends JpaRepository<Books,Long> {


}
