package com.deloitte.spring.springboot.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.deloitte.spring.springboot.jpa.dao.BookRepository;
import com.deloitte.spring.springboot.jpa.entity.Book;

@Component
@Repository
public class BookServiceImpl implements BookService{
	private BookRepository repository;

	public BookServiceImpl(BookRepository repository) {
		this.repository = repository;
	}
	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Optional<Book> getBookById(long id) {
		// TODO Auto-generated method stub
		 Optional<Book> bookById = repository.findById(id);
	      Book book = bookById.isPresent() ? bookById.get():null;
	         return repository.findById(id);
	
	}


	@Override
	public Book saveBook(Book book) {
		// TODO Auto-generated method stub
		return repository.save(book);
	}

	@Override
	public void deleteBook(long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	
	}
	

}
