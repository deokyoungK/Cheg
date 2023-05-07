package com.likelion.cheg.web.dto.product;

import com.likelion.cheg.annotation.numeric.Numeric;
import com.likelion.cheg.annotation.phone.Phone;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
public class ProductUploadDto {
    @NotBlank
    private String category;
    @NotBlank
    private String brand;
    @NotBlank
    private String name;
    private String description;

    @Min(1) @NotNull
    private Integer price;

    private MultipartFile file;

    @Min(1) @NotNull
    private Integer stockQuantity;
}
