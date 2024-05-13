package com.example.minor1;

import com.example.minor1.Exception.TrxException;
import com.example.minor1.model.Trx;
import com.example.minor1.repository.TrxRepo;
import com.example.minor1.request.TrxCreateRequest;
import com.example.minor1.services.BookService;
import com.example.minor1.services.StudentService;
import com.example.minor1.services.TrxService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;


import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestTrxService {

    @InjectMocks // INJECTING ALL THE MOCKS IN THIS SERVICE
    private  TrxService trxService;
    @Mock
    private TrxRepo trxRepo;

    @Mock
    private BookService bookService;


    @Mock
    StudentService studentService;

//    private TrxService trxService;


    @Before // this before is used to get this method call everytime befor calling any of the method with @Test , prevent us to do repetitive task everytime
   public void SetUp(){
//         trxService = new TrxService(); // here instead of creating object like this better way is to use spring to do all these task by @mock
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(trxService,"validUpto","14");
        ReflectionTestUtils.setField(trxService,"fineAmount",1);
    }

    @Test //testing each and every method before deployment of Server this is unit testing
    public void TestGetSettlementAmount(){
        // can check by creating custom dates as well;
        Trx trx = Trx.builder().createdOn(new Date()).paidAmnt(200).build();
//        TrxService trxService = new TrxService();
        int amount = trxService.getSettlementAmount(trx);

        Assert.assertEquals(180,amount);

    }


    //testing for filterstudent method in service class

    // i have to create a dependent objects for student student service which also depend on student repo this is done by spring boot using Mock annotation



    @Test(expected = TrxException.class)
    public  void TestStudentFilterMethodForNull() throws TrxException {

        when(studentService.filterBy(any(),any(),any())).thenReturn(null); // is line ka matlab hai filter by me kuch bhi ae lekin return hoga null
        // is test pe trxservice kya throw krta h ye test krna h
        TrxCreateRequest trxCreateRequest = TrxCreateRequest.builder().studentContact((long)213324).build() ;
        trxService.filterStudent(trxCreateRequest) ;


    }

    @Test(expected = TrxException.class)
    public  void TestStudentFilterMethodForEmptyList() throws TrxException {

        when(studentService.filterBy(any(),any(),any())).thenReturn(new ArrayList<>()); // is line ka matlab hai filter by me kuch bhi ae lekin return hoga null
        // is test pe trxservice kya throw krta h ye test krna h
        TrxCreateRequest trxCreateRequest = TrxCreateRequest.builder().studentContact((long)213324).build() ;
        trxService.filterStudent(trxCreateRequest) ;


    }

}
