package com.deloitte.spring.springboot.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.spring.springboot.jpa.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
