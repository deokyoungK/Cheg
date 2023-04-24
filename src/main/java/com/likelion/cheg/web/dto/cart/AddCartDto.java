package com.likelion.cheg.web.dto.cart;


import lombok.Data;

@Data
public class AddCartDto {
    private int productId;
    private int productCount;
}
