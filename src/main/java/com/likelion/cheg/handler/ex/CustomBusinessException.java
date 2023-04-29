package com.likelion.cheg.handler.ex;

public class CustomBusinessException extends RuntimeException{

    public CustomBusinessException(String message){
        super(message);
    }
}
