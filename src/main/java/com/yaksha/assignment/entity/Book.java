package com.yaksha.assignment.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String author;

	// Mark the back reference to prevent infinite recursion
	@ManyToMany(mappedBy = "books")
	@JsonBackReference
	private Set<BookClub> bookClubs = new HashSet<>();
	
	public Book(Long id, String title, String author, Set<BookClub> bookClubs) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.bookClubs = bookClubs;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Set<BookClub> getBookClubs() {
		return bookClubs;
	}

	public void setBookClubs(Set<BookClub> bookClubs) {
		this.bookClubs = bookClubs;
	}
}
