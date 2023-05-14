package com.likelion.cheg.web.apiController;

import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.web.dto.CMResponse;
import com.likelion.cheg.web.dto.product.ProductHomeResponseDto;
import com.likelion.cheg.web.dto.product.ProductUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class ProductApiController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    //상품 등록
    @PostMapping("/api/upload-product")
    public ResponseEntity<CMResponse> uploadProduct(@Validated ProductUploadDto productUploadDto){
        try {
            productService.addProduct(productUploadDto);
            return new ResponseEntity<>(new CMResponse(1, "상품 등록 성공", ""), HttpStatus.OK);
        } catch (CustomBusinessApiException e) {
            return new ResponseEntity<>(new CMResponse(-1, "상품 등록 실패", e.getErrorCode().getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new CMResponse(-1, "상품 등록 실패", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    //상품 삭제
    @PostMapping("api/{productId}/delete")
    public ResponseEntity deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(new CMResponse<>(1,"상품 삭제 성공",""),HttpStatus.OK);
    }

    //카테고리 아이디로 상품조회
    @GetMapping("api/products/category/{categoryId}")
    public ResponseEntity<CMResponse> getProductsByCategory(@PathVariable Integer categoryId){
        List<Product> productList = new ArrayList<>();
        if(categoryId == 0){ //전체 상품 조회
            productList = productRepository.findAllDesc();
        }else{
            productList = productRepository.findAllByCategoryId(categoryId);
        }
        List<ProductHomeResponseDto> productListDto = productService.makeHomeResponseDto(productList);
        return new ResponseEntity<>(new CMResponse<>(1,"카테고리별 상품조회 성공",productListDto),HttpStatus.OK);
    }

    //무한스크롤 상품 조회
    @GetMapping("api/products/{page}")
    public ResponseEntity<CMResponse> getProductList(@PathVariable int page){
        Page<Product> pageProductList = productService.getProductList(page, 4);
        List<Product> productList = pageProductList.getContent();
        List<ProductHomeResponseDto> productListDto = productService.makeHomeResponseDto(productList);
        return new ResponseEntity<>(new CMResponse<>(1,"상품 페이징 성공",productListDto),HttpStatus.OK);
    }


}
