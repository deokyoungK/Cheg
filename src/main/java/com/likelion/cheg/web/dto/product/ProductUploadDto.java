package com.likelion.cheg.web.dto.product;

import com.likelion.cheg.annotation.price.Price;
import com.likelion.cheg.domain.category.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class ProductUploadDto {

    @NotBlank
    private String category;
    @NotBlank
    private String brand;
    @NotBlank
    private String name;
    private String description;
    @Price
    private int price;
    private MultipartFile file;
}
