package com.example.minor1.controller;

import com.example.minor1.Exception.TrxException;
import com.example.minor1.model.Trx;
import com.example.minor1.request.StudentCreateRequest;
import com.example.minor1.request.TrxCreateRequest;
import com.example.minor1.request.TrxReturnRequest;
import com.example.minor1.services.TrxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Trx")
public class TrxController {

    @Autowired
    private TrxService trxService;

    @PostMapping("/create")
    public Trx create(@RequestBody TrxCreateRequest trxCreateRequest) throws TrxException{
        return trxService.create(trxCreateRequest);
    }

    @PostMapping("/return")
    public int returnBook(@RequestBody TrxReturnRequest trxReturnRequest) throws TrxException {
        return trxService.returnBook(trxReturnRequest); // returning the settlement amount for the return trx;
    }
}
