package com.likelion.cheg.web.dto.pay;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDto {
    private String address;
    private int productId;
    private int amount;
    private int flag; //상세인지 장바구니인지 구분

}
