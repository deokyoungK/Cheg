package com.likelion.cheg.admin;

import com.likelion.cheg.CommonMethod;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.category.CategoryRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.web.dto.product.ProductUploadDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class 상품관리 {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CommonMethod commonMethod;
    @Test
    public void 상품_등록(){
        //카테고리 생성
        String category_name = "NEW카테고리";
        commonMethod.createCategory(category_name);
        //카테고리 불러오기
        Category category = categoryRepository.findByCategoryName(category_name);
        //상품등록DTO생성
        String brand_name = "NEW브랜드";
        String product_name = "NEW상품";
        String description = "설명";
        String price = "10000";
        MockMultipartFile file = new MockMultipartFile("content", "NEW파일", "multipart/mixed", "".getBytes());

        ProductUploadDto productUploadDto = new ProductUploadDto();
        productUploadDto.setCategory(category_name);
        productUploadDto.setBrand(brand_name);
        productUploadDto.setName(product_name);
        productUploadDto.setDescription(description);
        productUploadDto.setPrice(price);
        productUploadDto.setFile(file);
        //상품 등록
        Product product = productService.addProduct(category,productUploadDto);

        assertEquals("상품 카테고리 확인",product.getCategory(),category);
//        assertEquals("상품 URL 확인",product.getUrl(),product.getUrl());
        assertEquals("상품 이름 확인",product.getName(),product_name);



    }
    @Test
    public void 상품_수정(){

    }
    @Test
    public void 상품_삭제(){

    }







}
