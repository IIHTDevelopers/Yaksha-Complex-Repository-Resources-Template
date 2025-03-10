package com.yaksha.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.yaksha.assignment.entity.BookClub;

@RepositoryRestResource
public interface BookClubRepository extends JpaRepository<BookClub, Long> {

    // Custom query to find a BookClub by name
    BookClub findByName(String name);
}
