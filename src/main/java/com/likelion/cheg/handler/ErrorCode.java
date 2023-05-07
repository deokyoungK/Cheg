package com.likelion.cheg.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    //Notfound
    NOT_FOUND_USER(HttpStatus.NOT_FOUND,"001","사용자를 찾을 수 없습니다."),
    NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND,"001","상품를 찾을 수 없습니다."),
    NOT_FOUND_CART(HttpStatus.NOT_FOUND,"001","장바구니를 찾을 수 없습니다."),
    NOT_FOUND_ORDER(HttpStatus.NOT_FOUND,"001","주문을 찾을 수 없습니다."),
    NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND,"001","카테고리를 찾을 수 없습니다."),
    NOT_FOUND_DELIVERY(HttpStatus.NOT_FOUND,"001","배송 정보를 찾을 수 없습니다."),

    //NOT ENOUGH
    NOT_ENOUGH_STOCK(HttpStatus.FORBIDDEN,"002","상품 재고가 부족합니다."),

    //회원가입 실패
    FAIL_SIGNUP(HttpStatus.BAD_REQUEST,"003","회원가입에 실패하였습니다."),


    //Point초과
    EXCEED_POINT(HttpStatus.FORBIDDEN,"004","보유한 포인트를 초과하였습니다."),

    //파일 업로드 실패
    FILE_UPLOAD_ERROR(HttpStatus.BAD_REQUEST, "005","이미지 파일 업로드에 실패하였습니다."),

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
