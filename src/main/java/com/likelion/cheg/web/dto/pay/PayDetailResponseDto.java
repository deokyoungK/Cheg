package com.likelion.cheg.web.dto.pay;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayDetailResponseDto {
    private int id;
    private String url;
    private String brand;
    private String name;
    private int price;
    private int amount;
    private int totalPrice;
}
