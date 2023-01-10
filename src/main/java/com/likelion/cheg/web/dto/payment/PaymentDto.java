package com.likelion.cheg.web.dto.payment;

import lombok.Data;

@Data
public class PaymentDto {

    private int user_id;
    private String address;
    private int product_id;
    private int amount;
    private int flag; //회원중에서 상세인지 장바구니인지 구분

}
