package com.likelion.cheg.handler;


import com.likelion.cheg.handler.ex.CustomValidationException;
import com.likelion.cheg.util.Script;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        if(e.getErrorMap() == null) {
            return Script.back(e.getMessage());
        }else {
            return Script.back(e.getErrorMap().toString());
        }
    }
}
