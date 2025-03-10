package com.yaksha.assignment.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yaksha.assignment.entity.Book;
import com.yaksha.assignment.entity.BookClub;

public class MasterData {

	// Create a sample Book instance
	public static Book getBook() {
		Book book = new Book();
		book.setId(1L); // Setting the ID for the book
		book.setTitle("Sample Book");
		book.setAuthor("John Doe");

		// Setting an empty Set of BookClubs (we'll add the BookClub later)
		book.setBookClubs(new HashSet<>());

		return book;
	}

	// Create a list of books
	public static List<Book> getBookList() {
		List<Book> bookList = new ArrayList<>();
		bookList.add(getBook()); // Adding a sample book
		return bookList;
	}

	// Create a page of books
	public static Page<Book> getBookPage(int page, int size) {
		List<Book> bookList = getBookList(); // Get list of books
		PageRequest pageRequest = PageRequest.of(page, size); // Set pagination
		return new PageImpl<>(bookList, pageRequest, bookList.size()); // Return page
	}

	// Create a sample BookClub instance
	public static BookClub getBookClub() {
		BookClub bookClub = new BookClub();
		bookClub.setId(1L); // Setting the ID for the book club
		bookClub.setName("Sci-Fi Lovers");

		// Adding a sample book to the book club
		Set<Book> books = new HashSet<>();
		Book sampleBook = getBook(); // Getting the sample book
		books.add(sampleBook);
		bookClub.setBooks(books); // Adding books to the club

		// Add this BookClub to the book's bookClubs Set as well
		sampleBook.getBookClubs().add(bookClub); // Adding bookClub to the book's clubs

		return bookClub;
	}

	// Create a list of book clubs
	public static List<BookClub> getBookClubList() {
		List<BookClub> bookClubList = new ArrayList<>();
		bookClubList.add(getBookClub()); // Adding a sample book club
		return bookClubList;
	}

	// Create a page of book clubs
	public static Page<BookClub> getBookClubPage(int page, int size) {
		List<BookClub> bookClubList = getBookClubList(); // Get list of book clubs
		PageRequest pageRequest = PageRequest.of(page, size); // Set pagination
		return new PageImpl<>(bookClubList, pageRequest, bookClubList.size()); // Return page
	}

	// Convert an object to a JSON string
	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule()); // Register the JavaTimeModule
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Disable writing dates as timestamps
			final String jsonContent = mapper.writeValueAsString(obj); // Convert object to JSON string
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e); // Handle exceptions during JSON conversion
		}
	}

	// Generate a random string of the given size (not used directly in this class)
	public static String randomStringWithSize(int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append("A");
		}
		return s.toString(); // Return the generated string
	}
}
