package com.yaksha.assignment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yaksha.assignment.entity.Book;
import com.yaksha.assignment.entity.BookClub;
import com.yaksha.assignment.repository.BookClubRepository;
import com.yaksha.assignment.repository.BookRepository;

@RestController
public class BookClubController {

	@Autowired
	private BookClubRepository bookClubRepository;

	@Autowired
	private BookRepository bookRepository;

	// Custom endpoint to list all books in a specific BookClub
	@GetMapping("/bookClubs/{bookClubId}/books")
	public ResponseEntity<List<Book>> getBooksByBookClub(@PathVariable("bookClubId") Long bookClubId) {
		BookClub bookClub = bookClubRepository.findById(bookClubId).orElse(null);
		if (bookClub != null) {
			// Convert Set to List and return
			List<Book> books = new ArrayList<>(bookClub.getBooks());
			return ResponseEntity.ok(books);
		}
		return ResponseEntity.notFound().build();
	}

	// Custom endpoint to get all books by BookClub's name
	@GetMapping("/bookClubs/name/{name}/books")
	public ResponseEntity<List<Book>> getBooksByBookClubName(@PathVariable("name") String name) {
		BookClub bookClub = bookClubRepository.findByName(name);
		if (bookClub != null) {
			// Convert Set to List and return
			List<Book> books = new ArrayList<>(bookClub.getBooks());
			return ResponseEntity.ok(books);
		}
		return ResponseEntity.notFound().build();
	}
}
