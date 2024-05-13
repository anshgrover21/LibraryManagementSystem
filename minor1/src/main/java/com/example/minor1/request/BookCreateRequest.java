package com.example.minor1.request;

import com.example.minor1.model.Author;
import com.example.minor1.model.Book;
import com.example.minor1.model.BookType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateRequest {

    // this contains what book request should contains
    // use of this class is to make front user unaware of database or table format
    // another is to manage class and data need to be entered as it is independent


    // this data need to be entered while adding request book to the book table

    @NotBlank(message = "Name Cannot Be blank")
    private String name;


    @NotBlank(message = "BookNo Cannot Be blank")
    private String bookNo;


    private BookType type;

    @Positive(message = "Cost must be positive")
    private Integer cost;

    private String authorName;

    private String authorEmail;

    public Author toAuthor() {
// builder is wasy way to create object for any class
        return Author.builder().name(this.authorName).email(this.authorEmail).build(); //this use to create author object with author name from this class and author email

    }



    public Book toBook() {
        
        return Book.builder().name(this.getName()).bookNo(this.getBookNo()).type(this.getType()).cost(this.getCost()).build();
    }

}
