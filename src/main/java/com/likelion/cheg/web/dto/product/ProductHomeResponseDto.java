package com.likelion.cheg.web.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductHomeResponseDto {
    private int id;
    private String url;
    private String brand;
    private String name;
    private int price;
}
