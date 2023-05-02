package com.likelion.cheg.web.dto.cart;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddCartDto {
    private int productId;
    private int productCount;
}
