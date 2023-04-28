package com.likelion.cheg.web.dto.pay;

import lombok.Data;



@Data
public class PayCartResponseDto {
    private String productUrl;
    private String productBrand;
    private String productName;
    private int productCount;
    private int cartTotalPrice;
}
