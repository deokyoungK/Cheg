package com.likelion.cheg.web.dto.cart;

import lombok.Data;

@Data
public class CartResponseDto {
    private int id;
    private int productCount;
    private int cartTotalPrice;
    private String productUrl;
    private String productName;
}
