package com.likelion.cheg.service;

import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.category.CategoryRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.handler.ex.CustomBusinessException;
import com.likelion.cheg.web.dto.product.ProductDetailResponseDto;
import com.likelion.cheg.web.dto.product.ProductHomeResponseDto;
import com.likelion.cheg.web.dto.product.ProductResponseDto;
import com.likelion.cheg.web.dto.product.ProductUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CartRepository cartRepository;

    @Value("${file.path}")
    String uploadFolder;

    public List<ProductHomeResponseDto> makeHomeResponseDto(List<Product> productList){
        List<ProductHomeResponseDto> productListDtos = productList.stream()
                .map(product -> new ProductHomeResponseDto(
                        product.getId(),
                        product.getUrl(),
                        product.getBrand(),
                        product.getName(),
                        product.getPrice()))
                .collect(Collectors.toList());
        return productListDtos;
    }

    public ProductDetailResponseDto makeDetailResponseDto(Product product){
        ProductDetailResponseDto productDto = new ProductDetailResponseDto(
                product.getId(),
                product.getUrl(),
                product.getBrand(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity()
        );
        return productDto;
    }

    public List<ProductResponseDto> makeResponseDto(List<Product> productList){
        List<ProductResponseDto> productListDtos = productList.stream()
                .map(product -> new ProductResponseDto(
                        product.getId(),
                        product.getCategory(),
                        product.getBrand(),
                        product.getUrl(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStockQuantity()))
                .collect(Collectors.toList());
        return productListDtos;
    }

    @Transactional
    public void deleteProduct(int productId){
        try{
            productRepository.deleteById(productId); //상품 삭제
            cartRepository.deleteByProductId(productId); //상품 관련 장바구니 삭제

        }catch(Exception e){
            throw new CustomBusinessException(e.getMessage());
        }
    }

    @Transactional
    public Product addProduct(ProductUploadDto productUploadDto){
        UUID uuid = UUID.randomUUID();
        //url 파싱
        String imageFileName = uuid+"_"+productUploadDto.getFile().getOriginalFilename();
        //카테고리 파싱
        Category category = categoryRepository.findByCategoryName(productUploadDto.getCategory());

        // 통신, I/O -> 예외가 발생할 수 있다.
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
        try {
            Files.write(imageFilePath,productUploadDto.getFile().getBytes());
        }catch(Exception e) {
            e.printStackTrace();
        }

        //상품 생성 후 저장
        Product product = Product.createProduct(category,
                productUploadDto.getBrand(),
                productUploadDto.getName(),
                productUploadDto.getDescription(),
                productUploadDto.getPrice(),
                imageFileName,
                productUploadDto.getStockQuantity());

        productRepository.save(product);
        return product;
    }

    @Transactional
    public List<Product> loadProductsDESC(){
        List<Product> productList = productRepository.findAllDesc();
        return productList;
    }


}
