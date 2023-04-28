package com.likelion.cheg.web.dto.product;


import com.likelion.cheg.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class ProductResponseDto {
    private int id;
    private Category category;
    private String brand;
    private String url;
    private String name;
    private String description;
    private int price;
}
