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
