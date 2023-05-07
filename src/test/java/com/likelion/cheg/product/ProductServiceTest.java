package com.likelion.cheg.product;

import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.category.CategoryRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.web.dto.product.ProductUploadDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void 상품등록_성공() throws Exception {
        //given
        Category category = Category.createCategory("신발");
        String categoryName = "신발";
        given(categoryRepository.findByCategoryName(categoryName)).willReturn(category);

        String brand = "에어포스";
        String name = "마이신발";
        String description = "에어포스 신발 마이신발입니다.";
        int price = 10000;
        int stockQuantity = 10;

        //파일 생성
        MockMultipartFile imageFile = new MockMultipartFile(
                "tempImage",
                "tempImage.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "Test image".getBytes()
        );

        ProductUploadDto productUploadDto = ProductUploadDto.builder()
                .category(categoryName)
                .brand(brand)
                .name(name)
                .description(description)
                .price(price)
                .file(imageFile)
                .stockQuantity(stockQuantity)
                .build();

        // when
        Product product = productService.addProduct(productUploadDto);

        //then
        assertEquals("상품 카테고리 확인 ",product.getCategory().getName(),categoryName);
        assertEquals("상품 브랜드 확인 ",product.getBrand(),brand);
        assertEquals("상품 이름 확인 ",product.getName(),name);
        assertEquals("상품 설명 확인 ",product.getDescription(),description);
        assertEquals("상품 가격 확인 ",product.getPrice(),price);
        assertEquals("상품 file경로 확인 ",product.getUrl().contains(imageFile.getOriginalFilename()),true);
        assertEquals("상품 재고 확인 ",product.getStockQuantity(),stockQuantity);

    }
    @Test
    public void 상품등록_실패_파일업로드오류() throws Exception {
        //given
        String category = "신발";
        String brand = "에어포스";
        String name = "마이신발";
        String description = "에어포스 신발 마이신발입니다.";
        int price = 10000;
        int stockQuantity = 10;

        //파일 mocking
        MultipartFile imageFile = mock(MultipartFile.class);
        Mockito.when(imageFile.getBytes()).thenThrow(new IOException()); //파일

        ProductUploadDto productUploadDto = ProductUploadDto.builder()
                .category(category)
                .brand(brand)
                .name(name)
                .description(description)
                .price(price)
                .file(imageFile)
                .stockQuantity(stockQuantity)
                .build();
        //when
        Throwable e = assertThrows(CustomBusinessApiException.class,
                () -> productService.addProduct(productUploadDto));

        // then
        assertEquals(ErrorCode.FILE_UPLOAD_ERROR, ((CustomBusinessApiException) e).getErrorCode());
    }

}
