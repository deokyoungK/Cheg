package com.likelion.cheg.web.dto.cart;

import lombok.Data;

@Data
public class CartResponseDto {

    private int cartId;
    private int productCount;
    private int cartTotalPrice;
}
