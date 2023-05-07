package com.likelion.cheg.web.dto.product;

import com.likelion.cheg.annotation.numeric.Numeric;
import com.likelion.cheg.annotation.phone.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
