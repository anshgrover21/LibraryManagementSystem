package com.example.minor1.services;

import com.example.minor1.model.*;
import com.example.minor1.repository.AuthorRepo;
import com.example.minor1.repository.BookRepo;
import com.example.minor1.request.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;

    public Book createBook(BookCreateRequest bookCreateRequest) {
            // now we get the data here from bookcreateRequest method and seperate the author data and book data to fill the table; we will write logic here to manage data

        // first check wether the author with the given name is already present in author table or not
        Author authorFromDb =authorRepo.findByEmail(bookCreateRequest.getAuthorEmail());
        if(authorFromDb==null)// means not present we can add it in author table;
        {
          authorFromDb = authorRepo.save(bookCreateRequest.toAuthor());
        }
        // here we create toAuthor method in book create request to get author data and similarly will create for book to get book data
        Book book = bookCreateRequest.toBook();
        // also need to set author in this book class as well
        book.setAuthor(authorFromDb);
       return bookRepo.save(book);

    }

    public List<Book> filterBy(Prefix prefix, Operator operator, String value) {

        switch (operator){
            case EQUAL : switch (prefix){
                case BOOKNAME : return bookRepo.findByName(value);
                case AUTHORNAME: return bookRepo.findByAuthorName(value);
                case BOOKNO: return bookRepo.findByBookNo(value);
                case COST:return  bookRepo.findByCost(Integer.valueOf(value));
                case BOOKTYPE: return bookRepo.findByType(BookType.valueOf(value));

                         }
            case GREATERTHAN: switch (prefix){
                case COST :return bookRepo.findByCostGreaterThan(Integer.valueOf(value));

            }
            default:return new ArrayList<Book>();
        }

    }

}
