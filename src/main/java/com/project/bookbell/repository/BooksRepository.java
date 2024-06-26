package com.project.bookbell.repository;

import com.project.bookbell.domain.Books;
import com.project.bookbell.domain.QBooks;
import com.project.bookbell.dto.BooksDto;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;


@RepositoryRestResource
@Repository
public interface BooksRepository extends
          JpaRepository<Books,Long>
        , QuerydslPredicateExecutor<Books> // 모든 필드 검색 기능 추가.
        , QuerydslBinderCustomizer<QBooks> //검색 세부 커스터마이징
    {





        @Override
        default void customize(QuerydslBindings bindings, QBooks root){
            bindings.excludeUnlistedProperties(true); // 모든필드 검색 제외하기 - true
            bindings.including(root.title,root.author,root.ISBN);
            //bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like ''
            bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%%
            bindings.bind(root.author).first(StringExpression::containsIgnoreCase);
            bindings.bind(root.ISBN).first(StringExpression::containsIgnoreCase);
//            bindings.bind(root.id).first(NumberPath::eq);
        }

        Page<BooksDto> findByTitleContaining(String title, Pageable pageable);

        Page<BooksDto> findByAuthorContaining(String author, Pageable pageable);

        List<Books> findByLibraryId(Long libraryId);






    }
