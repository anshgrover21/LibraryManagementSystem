package com.example.minor1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String name;


    @Column(unique = true)
    private String bookNo;


    @Enumerated(value = EnumType.STRING)
    private BookType type;


    @Column
    private Integer cost;

    //book is storing student means the book table contains student with there unique id which is foriegn key for
    // book table and multiple book can have one student so relationship is many to one......similarly
    //in student table also there is one to many relationship which need to me justified in its class as well and mapped to this book class


    @ManyToOne
    @JoinColumn // this is telling that this relation ship is happening that is book is storing student not student is storing list of book
    @JsonIgnore
    private Student student; //this name has to be mapped in class Student

    @ManyToOne
    @JoinColumn// this basically add a collumn author to the table book jha bhi ye joincolumn use kroge vha ek naya column a ajega
    @JsonIgnore
    private  Author author;

    //note : hua kya uper book me hai author and author me h list of book to recursion bngya htae ise jsonignore ki madad se hataenge

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Trx> trxList;



}
