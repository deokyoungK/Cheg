package com.likelion.cheg.admin;

import com.likelion.cheg.CommonMethod;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.category.CategoryRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.web.dto.product.ProductUploadDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
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
    @Autowired
    ProductRepository productRepository;

    @Before
    public void set_up(){

    }
    @Test
    public void 상품_등록(){
        //카테고리 생성
        String cname = "스웨터";
        commonMethod.createCategory(cname);

        //카테고리 불러오기
        Category category = categoryRepository.findByCategoryName(cname);

        //상품등록DTO생성
        String brand_name = "NEW브랜드";
        String product_name = "NEW상품";
        String description = "설명";
        String price = "10000";
        MockMultipartFile file = new MockMultipartFile("content", "NEW파일", "multipart/mixed", "".getBytes());

        ProductUploadDto productUploadDto = new ProductUploadDto();
        productUploadDto.setCategory(cname);
        productUploadDto.setBrand(brand_name);
        productUploadDto.setName(product_name);
        productUploadDto.setDescription(description);
        productUploadDto.setPrice(price);
        productUploadDto.setFile(file);

        //상품 등록
        Product product = productService.addProduct(category,productUploadDto);

        assertEquals("상품 카테고리 확인",product.getCategory(),category);
        assertEquals("상품 URL 확인",product.getUrl().contains(file.getOriginalFilename()),true);
        assertEquals("상품 이름 확인",product.getName(),product_name);
        assertEquals("상품 가격 확인",product.getPrice(), Integer.parseInt(price));
    }
    @Test
    public void 상품_카테고리변경(){
        //카테고리 생성
        String cname = "old카테고리";
        String cname2 = "new카테고리";
        Category old_category = commonMethod.createCategory(cname);
        Category new_category = commonMethod.createCategory(cname2);

        //상품 생성
        String product_name = "NEW상품";
        Product product = commonMethod.createProduct(old_category,product_name,1000);

        //카테고리 변경
        product.setCategory(new_category);

        assertEquals("상품의 바뀐 카테고리 확인",product.getCategory(),new_category);
        assertEquals("상품의 바뀐 카테고리 이름 확인",product.getCategory().getName(),cname2);
    }


    @Test
    public void 상품_삭제(){
        //카테고리 생성
        String cname = "패딩";
        Category category = commonMethod.createCategory(cname);

        //상품 생성
        String pname = "상품1";
        String pname2 = "상품2";
        Product product = commonMethod.createProduct(category,pname,2000);
        Product product2 = commonMethod.createProduct(category,pname2,1500);

        //상품 1번 삭제
        productService.deleteProduct(product.getId());

        List<Product> productList = productRepository.findAll();
        assertEquals("상품1이 없어야 함",productList.contains(product),false);
        assertEquals("상품2이 있어야 함",productList.contains(product2),true);

    }
}
