package com.example.minor1.model;


import com.example.minor1.request.BookCreateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String name;

    @Column(length = 30, unique = true , nullable = false)
    private String email;

    @CreationTimestamp // use to create a time when ever date is added
    private Date createdOn;

    @UpdateTimestamp // update the time
    private Date updatedOn;

    @OneToMany(mappedBy = "author")// column in book which it need to be matched
    private List<Book> booklist;



}
