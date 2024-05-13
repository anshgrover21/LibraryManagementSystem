package com.example.minor1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 15, unique = true, nullable = false)
    private String phoneNo;

    @Column(length = 30, unique = true)
    private String email;

    @Column(length = 30)
    private String name;

    private String address;

    @CreationTimestamp // use to create a time when ever date is added
    private Date createdOn;

    @UpdateTimestamp // update the time
    private Date updatedOn;

    @Enumerated(value = EnumType.STRING) // two type of enum , 1. ordinal (integer) 2. String (string) by default it is ordinal so we have to mention it if making string
    private StudentType status ;

    @OneToMany(mappedBy = "student")
    private List<Book> booklist;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Trx> trxList; // this is mapped to transecction table which consist of one student id for every transection id

}
