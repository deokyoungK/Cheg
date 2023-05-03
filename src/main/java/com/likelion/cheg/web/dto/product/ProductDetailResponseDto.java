package com.likelion.cheg.web.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailResponseDto {
    private int id;
    private String url;
    private String brand;
    private String name;
    private String description;
    private int price;
    private int stockQuantity;
}
