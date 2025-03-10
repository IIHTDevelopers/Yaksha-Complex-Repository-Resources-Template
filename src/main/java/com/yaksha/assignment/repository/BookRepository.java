package com.yaksha.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.yaksha.assignment.entity.Book;

@RepositoryRestResource(collectionResourceRel = "all-books", path = "books")
public interface BookRepository extends JpaRepository<Book, Long> {
}
