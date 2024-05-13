package com.example.minor1.services;

import com.example.minor1.Exception.TrxException;
import com.example.minor1.model.*;
import com.example.minor1.repository.TrxRepo;
import com.example.minor1.request.StudentCreateRequest;
import com.example.minor1.request.TrxCreateRequest;
import com.example.minor1.request.TrxReturnRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TrxService {

    @Autowired
    private TrxRepo trxRepo;

    @Autowired
    private BookService bookService;

    @Autowired StudentService studentService;

    public List<Student>  filterStudent(TrxCreateRequest trxCreateRequest) throws TrxException{

        List<Student> studentList = studentService.filterBy(StudentFilterType.CONTACT, Operator.EQUAL, trxCreateRequest.getStudentContact().toString());

        if(studentList ==null || studentList.isEmpty() ){
            throw  new TrxException("Student does't belong to Library");
        }

        return studentList;

    }


    @Transactional(rollbackOn = {TrxException.class}) // to make the transection synchronise
    public Trx create(TrxCreateRequest trxCreateRequest) throws TrxException {
        // 4 check i need to have

        //check 1 : checking for student is of the given library
        //use filter method of student Service for this
//
//        List<Student> studentList = studentService.filterBy(StudentFilterType.CONTACT, Operator.EQUAL, trxCreateRequest.getStudentContact().toString());
//
//        if(studentList.isEmpty() || studentList ==null){
//            throw  new TrxException("Student does't belong to Library");
//        }

        List<Student> studentList = filterStudent(trxCreateRequest);


        Student student = studentList.get(0);

        //check 2 weather book required by user or student is in library or is vacent or not

        List<Book> bookListFromLib = bookService.filterBy(Prefix.BOOKNO , Operator.EQUAL , trxCreateRequest.getBookNo());

        if(bookListFromLib ==null || bookListFromLib.isEmpty()){
            throw new TrxException("Book does't belong to Library");
        }

        Book bookfromLib = bookListFromLib.get(0);
        //check that book present in lib but issued by another student
        if(bookfromLib.getStudent() != null){
            throw  new TrxException("Book Already Issued ");
        }

        //step 3 making book active and creating trx id;

        String trxId = UUID.randomUUID().toString() ; // this cannot be used in large scale application as never create a unique id

        bookfromLib.setStudent(student);

        Trx trx = Trx.builder().trxId(trxId).book(bookfromLib).student(student).
                paidAmnt(trxCreateRequest.getPaidAmnt()).status(TrxStatus.ISSUED).build();

        return  trxRepo.save(trx);


    }

    @Value("${student.valid.days}")
    private String validUpto;

    @Value("${student.delayed.finePerDays}")
    private int fineAmount;

    public int getSettlementAmount( Trx trx) {
      long issueTime = trx.getCreatedOn().getTime();
      long currTime = System.currentTimeMillis();
      long timediff = currTime - issueTime;
      int daysPass= (int)TimeUnit.DAYS.convert(timediff,TimeUnit.MILLISECONDS);

      return daysPass>Integer.valueOf(validUpto)?trx.getPaidAmnt() - (daysPass-Integer.valueOf(validUpto))*fineAmount:trx.getPaidAmnt();
    }


    @Transactional(rollbackOn = {TrxException.class})
    public int returnBook(TrxReturnRequest trxReturnRequest) throws TrxException {
        // check 1 : checking for student present in trx db or not or Student present in db
        //check 2 : book returning is present in db or not
        // cehck 3 : status of book present in db that is if it is issued or not

       Trx trxFromDb=  trxRepo.findByTrxId(trxReturnRequest.getTrxId());

       if(trxFromDb==null){
           throw  new TrxException("No transection exist for the id given ");
       }

       Book bookWithId = trxFromDb.getBook();
       Student studentWithId =  trxFromDb.getStudent();
    int amount = getSettlementAmount(trxFromDb);
       if(trxFromDb.getPaidAmnt()==amount){
           trxFromDb.setStatus(TrxStatus.RETURNED);

       }
       else{
           trxFromDb.setStatus(TrxStatus.FINED);
       }

       trxFromDb.setPaidAmnt(amount);

       bookWithId.setStudent(null);

       return amount;



    }


    // method for returning book
}
