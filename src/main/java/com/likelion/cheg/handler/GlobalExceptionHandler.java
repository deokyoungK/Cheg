package com.likelion.cheg.handler;


import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.handler.ex.CustomBusinessException;
import com.likelion.cheg.util.Script;
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
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(HttpStatus.BAD_REQUEST.toString(), e.getBindingResult());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.toString(), e.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }

    /**
     * 비즈니스 로직 에러 -> API전달
     */
    @ExceptionHandler(CustomBusinessApiException.class)
    public ResponseEntity<ErrorResponse> handleBusinessApiException(CustomBusinessApiException e){
        log.error("handleCustomBusinessAPIException",e);
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(e.getErrorCode().getErrorCode(),e.getMessage());
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(errorResponse);
    }

    /**
     * 비즈니스 로직 에러 -> 바로 alert()
     */
    @ExceptionHandler(CustomBusinessException.class)
    public String handleBusinessException(CustomBusinessException e){
        log.error("handleCustomBusinessException",e);
        return Script.back(e.getMessage());
    }

    /**
     * 그 외 에러
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception", e);
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
