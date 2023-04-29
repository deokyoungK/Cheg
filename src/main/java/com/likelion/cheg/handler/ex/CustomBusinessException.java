package com.likelion.cheg.handler.ex;

import com.likelion.cheg.handler.ErrorCode;

public class CustomBusinessException extends RuntimeException{

    private ErrorCode errorCode;
    public CustomBusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
