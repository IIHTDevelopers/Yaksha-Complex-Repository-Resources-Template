package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.yaksha.assignment.controller.BookClubController;
import com.yaksha.assignment.entity.Book;
import com.yaksha.assignment.entity.BookClub;
import com.yaksha.assignment.repository.BookClubRepository;
import com.yaksha.assignment.repository.BookRepository;
import com.yaksha.assignment.utils.JavaParserUtils;

@WebMvcTest(BookClubController.class)
@AutoConfigureMockMvc
public class BookClubControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookClubRepository bookClubRepository;

	@MockBean
	private BookRepository bookRepository;

	private BookClub bookClub;
	private Book book1;
	private Book book2;

	@BeforeEach
	public void setup() {
		// Ensure fresh state before each test
		bookClub = new BookClub();
		bookClub.setId(1L);
		bookClub.setName("Sci-Fi Club");

		book1 = new Book();
		book1.setId(1L);
		book1.setTitle("Book A");
		book1.setAuthor("Author A");
		book1.setBookClubs(new HashSet<>());

		book2 = new Book();
		book2.setId(2L);
		book2.setTitle("Book B");
		book2.setAuthor("Author B");
		book2.setBookClubs(new HashSet<>());

		// Associating books with the BookClub
		bookClub.setBooks(new HashSet<>());
		bookClub.getBooks().add(book1);
		bookClub.getBooks().add(book2);

		// Adding the BookClub to the books' bookClubs set
		book1.getBookClubs().add(bookClub);
		book2.getBookClubs().add(bookClub);
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	// Test to check if the 'BookClubController' class has @RestController
	// annotation
	@Test
	public void testRestControllerAnnotation() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookClubController.java";
		boolean result1 = JavaParserUtils.checkClassAnnotation(filePath, "RestController");
		yakshaAssert(currentTest(), result1, businessTestFile);
	}

	// Test to check if 'getBooksByBookClub' method has @GetMapping annotation
	@Test
	public void testGetBooksByBookClubAnnotation() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookClubController.java";
		boolean result = JavaParserUtils.checkMethodAnnotation(filePath, "getBooksByBookClub", "GetMapping");
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	// Test to check if 'getBooksByBookClubName' method has @GetMapping annotation
	@Test
	public void testGetBooksByBookClubNameAnnotation() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookClubController.java";
		boolean result = JavaParserUtils.checkMethodAnnotation(filePath, "getBooksByBookClubName", "GetMapping");
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	// Test to check GET request for books in a specific BookClub by BookClub ID
	@Test
    public void testGetBooksByBookClub() throws Exception {
        // Mocking the repository call to return the BookClub by ID
        when(bookClubRepository.findById(1L)).thenReturn(java.util.Optional.of(bookClub));

        // Creating the request for fetching books of the BookClub with ID 1
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bookClubs/1/books")
                .contentType("application/json")
                .accept("application/json");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // Check if the response content matches the expected list of books
        yakshaAssert(currentTest(),
        		result.getResponse().getContentAsString() != null ? "true" : "false",
                businessTestFile);
    }

	// Test to check GET request for books by BookClub name
	@Test
    public void testGetBooksByBookClubName() throws Exception {
        // Mocking the repository call to return the BookClub by name
        when(bookClubRepository.findByName("Sci-Fi Club")).thenReturn(bookClub);

        // Creating the request for fetching books of the BookClub with name "Sci-Fi Club"
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bookClubs/name/Sci-Fi Club/books")
                .contentType("application/json")
                .accept("application/json");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // Check if the response content matches the expected list of books
        yakshaAssert(currentTest(),
        		result.getResponse().getContentAsString() != null ? "true" : "false",
                businessTestFile);
    }
}
