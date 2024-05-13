package com.example.minor1.repository;

import com.example.minor1.model.Book;
import com.example.minor1.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book,Integer> {

    List<Book> findByBookNo(String value);

    List<Book> findByName(String value);
    List<Book> findByAuthorName(String value);

    // three ways to write queries
    List<Book> findByCostGreaterThan(Integer Value);

    List<Book> findByType(BookType value);

    List<Book> findByCost(Integer value);



}
