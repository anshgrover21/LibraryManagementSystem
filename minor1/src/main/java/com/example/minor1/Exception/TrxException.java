package com.example.minor1.Exception;


//creating a new transection class where i am creating a new instance whenever i need to show exeption and
// giving the msg to the parent exception class which show the error stated

// this is one of good practice of creating a custom exception
public class TrxException extends Exception{
    public TrxException(String msg){
         super(msg);
    }

}
