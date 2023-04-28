package com.likelion.cheg.handler;


import com.likelion.cheg.handler.ex.CustomException;
import com.likelion.cheg.handler.ex.CustomValidationApiException;
import com.likelion.cheg.handler.ex.CustomValidationException;
import com.likelion.cheg.util.Script;
import com.likelion.cheg.web.dto.CMResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * javax.validation.Valid 또는 @Validated binding error가 발생할 경우
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e){
        log.error("handleBindException",e);
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST.toString(), e.getBindingResult());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

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
