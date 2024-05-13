package com.example.minor1.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trx {

    // this is the transaction table mapped with both book and student to maintain the transaction id ,
    // which manages every transection of the student

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String trxId;

    @JoinColumn // to display student column in Trx table
    @ManyToOne
    private Student student;

    @JoinColumn // to display student column in Trx table
    @ManyToOne
    private Book book;

    @CreationTimestamp // use to create a time when ever date is added
    private Date createdOn;

    @UpdateTimestamp // update the time
    private Date updatedOn;

    private Integer paidAmnt;

    @Enumerated(value = EnumType.STRING)
    private  TrxStatus status;


}
