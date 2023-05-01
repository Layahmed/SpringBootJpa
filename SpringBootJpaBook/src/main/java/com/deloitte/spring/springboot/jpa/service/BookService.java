package com.deloitte.spring.springboot.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.deloitte.spring.springboot.jpa.entity.Book;

@Repository
@Component

public interface BookService {


	List<Book> getAllBooks();
     Optional<Book> getBookById(long id);
     Book saveBook(Book book);
	
	 void deleteBook(long id);
}
