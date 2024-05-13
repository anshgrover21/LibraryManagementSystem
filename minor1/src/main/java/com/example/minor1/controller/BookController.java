package com.example.minor1.controller;

import com.example.minor1.model.Book;
import com.example.minor1.model.Operator;
import com.example.minor1.model.Prefix;
import com.example.minor1.request.BookCreateRequest;
import com.example.minor1.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {

    @Autowired
    private BookService bookService;

        @PostMapping("/create")
        public Book createBook(@RequestBody @Valid BookCreateRequest bookCreateRequest)
        {

                return bookService.createBook(bookCreateRequest);
        }

        //now i have to create a get request with various filter method to answer various query in one go

        @GetMapping("/filter")
      public List<Book> filterBy(@RequestParam("prefix") Prefix prefix, @RequestParam("operator") Operator operator ,@RequestParam("value") String value){
           return bookService.filterBy(prefix,operator,value);
        }
}
