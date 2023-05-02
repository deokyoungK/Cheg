package com.likelion.cheg.web.api;

import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.web.dto.CMResponse;
import com.likelion.cheg.web.dto.product.ProductHomeResponseDto;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@RequiredArgsConstructor
@Controller
public class ProductApiController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @PostMapping("api/{productId}/delete")
    public ResponseEntity deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(new CMResponse<>(1,"상품 삭제 성공",""),HttpStatus.OK);
    }

    @GetMapping("api/products/category/{categoryId}")
    public ResponseEntity<CMResponse> getProductsByCategory(@PathVariable int categoryId){
        List<Product> productList = new ArrayList<>();
        if(categoryId == 0){ //전체 상품 조회
            productList = productRepository.findAll();
        }else{
            productList = productRepository.findAllByCategoryId(categoryId);
        }
        List<ProductHomeResponseDto> productListDto = productService.makeHomeResponseDto(productList);
        return new ResponseEntity<>(new CMResponse<>(1,"카테고리별 상품조회 성공",productListDto),HttpStatus.OK);
    }
}
