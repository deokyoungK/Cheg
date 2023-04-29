package com.likelion.cheg.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    TEST_ERROR(HttpStatus.OK,"001","")



    ;
    ErrorCode(HttpStatus httpStatus, String errorCode,String message){
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
}
