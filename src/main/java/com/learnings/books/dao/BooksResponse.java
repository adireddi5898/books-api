package com.learnings.books.dao;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BooksResponse {
	private Object responseObject;
	private HttpStatus statusCode;
}
