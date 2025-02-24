package com.learnings.books.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.learnings.books.dao.Books;
import com.learnings.books.repo.BooksRepo;

@Service
public class BooksService {

	@Autowired
	private BooksRepo booksrepo;

	public List<Books> getAllBooks() {
		return booksrepo.findAll();
	}
	
	public Optional<Books> getBookById(Long id){
		return booksrepo.findById(id);
	}

}
