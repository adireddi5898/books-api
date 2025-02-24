package com.learnings.books.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnings.books.dao.Books;
import com.learnings.books.dao.BooksResponse;
import com.learnings.books.repo.BooksRepo;
import com.learnings.books.services.BooksService;

@RestController
@RequestMapping("/api/books")
public class BooksController {
	@Autowired
	private BooksRepo booksrepo;
	@Autowired
	private BooksService booksService;
	@Autowired
	private JmsTemplate jmsTemp;

	@PostMapping("/saveBook")
	public ResponseEntity<BooksResponse> addBook(@RequestBody Books books) {
//		jmsTemp.convertAndSend("BooksQueue", books, message -> {
//			message.setStringProperty("contentType", "application/json");
//			return message;
//		});
//		Books savedBook = booksrepo.save(books);
		booksrepo.save(books);
		BooksResponse resp = new BooksResponse();
		resp.setStatusCode(HttpStatus.CREATED);
		resp.setResponseObject(books);
		return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}

	@GetMapping("/getbooks")
	public List<Books> getAllBooks() {
		List<Books> books = booksService.getAllBooks();
		return books;
	}

	/*
	 * @GetMapping("/{id}") public ResponseEntity<Books> getAllBook(@PathVariable
	 * Long id) { return booksrepo.findById(id).map(books -> { Books response = new
	 * Books(); BeanUtils.copyProperties(books, response); return new
	 * ResponseEntity<>(response, HttpStatus.OK); }).orElse(new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND)); }
	 */

	@GetMapping("/byid/{id}")
	public ResponseEntity<Books> getAllBooks(@PathVariable("id") Long id) {
		return booksrepo.findById(id).map(books -> {
			Books response = new Books();
			BeanUtils.copyProperties(books, response);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}