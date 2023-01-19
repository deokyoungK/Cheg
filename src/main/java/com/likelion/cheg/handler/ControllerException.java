package com.likelion.cheg.handler;


import com.likelion.cheg.handler.ex.CustomException;
import com.likelion.cheg.handler.ex.CustomValidationApiException;
import com.likelion.cheg.handler.ex.CustomValidationException;
import com.likelion.cheg.util.Script;
import com.likelion.cheg.web.dto.CMResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerException {

    //클라이언트통신 - javascript리턴
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        if(e.getErrorMap() == null) {
            return Script.back(e.getMessage());
        }else {
            return Script.back(e.getErrorMap().toString());
        }
    }

    @ExceptionHandler(CustomException.class)
    public String Exception(CustomException e){
        return Script.back(e.getMessage());
    }


    //api통신 - 데이터리턴
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<CMResponseDto<?>> validationApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMResponseDto<>(-1,e.getMessage(),e.getErrorMap()), HttpStatus.BAD_REQUEST);

    }
}
