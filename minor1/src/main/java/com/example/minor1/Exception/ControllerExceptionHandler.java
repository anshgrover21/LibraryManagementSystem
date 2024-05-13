package com.example.minor1.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = TrxException.class)
    public ResponseEntity<Object> handle(TrxException trxException){
        return new ResponseEntity<>(trxException.getMessage(), HttpStatus.BAD_REQUEST);

    }


    // IN THIS WE ARE USING DIRECT exception class , this is used to handle global exception that is server has some problem

        @ExceptionHandler(value =Exception.class)
    public ResponseEntity<Object> handle(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

     @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> notValid(MethodArgumentNotValidException m){
        return new ResponseEntity<>(m.getFieldError().getDefaultMessage().toString() , HttpStatus.BAD_REQUEST);
     }
}
