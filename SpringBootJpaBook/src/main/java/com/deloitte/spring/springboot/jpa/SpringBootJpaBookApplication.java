package com.deloitte.spring.springboot.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.deloitte.spring.springboot.jpa.dao.BookRepository;
import com.deloitte.spring.springboot.jpa.entity.Book;
import com.deloitte.spring.springboot.jpa.service.*;

@Repository
@SpringBootApplication

public class SpringBootJpaBookApplication {

	private static Scanner scan = new Scanner(System.in);
	
	 
	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(SpringBootJpaBookApplication.class, args);
		
		BookService bookService = appContext.getBean(BookService.class);
     
    
     while (true) {
    	  
     System.out.println("Menu: Select an option ");
     System.out.println("1, List all books");
     System.out.println("2, Add a new book");
     System.out.println("3, update an existing book");
     System.out.println("4, Delete a book");
     System.out.println("5, Exit");
     
     int option = scan.nextInt();
     scan.nextLine();
     
     switch (option) {
     case 1: 
    	 listAllBooks(bookService);
     break;
     case 2: 
    	 addBook(bookService);
     break;
     case 3: 
    	 updateBook(bookService);
    	 break;
     case 4:
    	 deleteBook(bookService);
    	 break;
     case 5:
    	 System.out.println("Thankyou!");
    	 break;
    	 default:
    		 System.out.println("Invalid option");
    		 break;
     
     }
    
     } 
     
	}
    
	  private static void listAllBooks(BookService bookService) {
 		System.out.println("List of All Books: ");
 		
 		
		bookService.getAllBooks().forEach(System.out::println);
 	}
	
	 private static void addBook(BookService bookService) {
			System.out.println("Please enter the book details");
				System.out.println("Title: ");
				String title = scan.nextLine();
				System.out.println("Author: ");
				 String author = scan.nextLine();
				 System.out.println("Price: ");
				 BigDecimal price = scan.nextBigDecimal();
				 System.out.println("Publication Date (yyyy-MM-dd): ");
				 
				 String dateString = scan.next();
					String[] array = dateString.split("-");
					
				    int year = Integer.parseInt(array[0]);
				    int  month = Integer.valueOf(array[1]);
					int day = Integer.parseInt(array[2]); 
					LocalDate publicationDate = LocalDate.of(year, month, day);
					
	 Book book = new Book(title, author, price, publicationDate);
	 bookService.saveBook(book);
	 System.out.println("Book added successfully");
	 
	 }
	 
	 
	 

	private static void updateBook(BookService bookService) {
			
			System.out.println("Please enter the Id of the book you want to update: ");
			long id = scan.nextLong();
			scan.nextLine();
			Optional<Book> optionalBook = bookService.getBookById(id);
			if(optionalBook.isPresent()) {
			Book book = optionalBook.get();
			
			System.out.println("Detail: " + book);
			System.out.println("please enter the new details: ");
			
			System.out.println("Title: ");
			String title = scan.nextLine();
			if(!title.isEmpty()) {
				book.setTitle(title);
			}
			
			System.out.println("Author: ");
			String author = scan.nextLine();
			if(!author.isEmpty()) {
				book.setAuthor(author);
			}
			
			 System.out.println("Price: ");
			 BigDecimal price = scan.nextBigDecimal();
			 
			 System.out.println("Publication Date (yyyy-MM-dd): ");
			 String dateString = scan.next();
				String[] array = dateString.split("-");
				
			    int year = Integer.parseInt(array[0]);
			    int  month = Integer.valueOf(array[1]);
				int day = Integer.parseInt(array[2]); 
				LocalDate publicationDate = LocalDate.of(year, month, day);
				
			 book.setTitle(title);
			 book.setAuthor(author);
			 book.setPrice(price);
			 book.setPublicationDate(publicationDate);
			 bookService.saveBook(book);
			 System.out.println("Book updated successfully");
			 
		 }else {
			 System.out.println("Book not found" + " "+ id);
		 }
	 }
	 
	 private static void deleteBook(BookService bookService) {
		 System.out.println("Please enter the Id of the book you want to delete: ");
		 long id = scan.nextLong();
		 scan.nextLine();
		 bookService.deleteBook(id);
		 System.out.println("Book deleted successfully");
	 }
     }

