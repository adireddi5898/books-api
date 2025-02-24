package com.learnings.books.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnings.books.dao.Books;

public interface BooksRepo extends JpaRepository<Books, Long> {

}
