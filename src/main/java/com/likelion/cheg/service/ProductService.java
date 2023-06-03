package com.likelion.cheg.service;

import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.category.CategoryRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.stock.Stock;
import com.likelion.cheg.domain.stock.StockRepository;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.handler.ex.CustomBusinessException;
import com.likelion.cheg.web.dto.product.ProductDetailResponseDto;
import com.likelion.cheg.web.dto.product.ProductHomeResponseDto;
import com.likelion.cheg.web.dto.product.ProductResponseDto;
import com.likelion.cheg.web.dto.product.ProductUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
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
    private final StockRepository stockRepository;

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
                product.getStock().getQuantity()
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
                        product.getStock().getQuantity()))
                .collect(Collectors.toList());
        return productListDtos;
    }

    //무한 스크롤 조회
    public Page<Product> getProductList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Product> productList = productRepository.findAll(pageable);
        return productList;
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
    public Product addProduct(ProductUploadDto productUploadDto) throws Exception {
        try{
            //url 파싱
            String imageFileName = productUploadDto.getFile().getOriginalFilename();
            //카테고리 파싱
            Category category = categoryRepository.findByCategoryName(productUploadDto.getCategory());

            //이미지 파일 저장
            Path imageFilePath = Paths.get(uploadFolder+imageFileName);
            Files.write(imageFilePath,productUploadDto.getFile().getBytes());

            //재고 생성
            Stock stock = Stock.builder()
                    .quantity(productUploadDto.getStockQuantity())
                    .build();

            //상품 생성
            Product product = Product.createProduct(category,
                    productUploadDto.getBrand(),
                    productUploadDto.getName(),
                    productUploadDto.getDescription(),
                    productUploadDto.getPrice(),
                    imageFileName,
                    stock);

            stockRepository.save(stock);
            productRepository.save(product);
            return product;

        } catch(IOException e){
            throw new CustomBusinessApiException(ErrorCode.FILE_UPLOAD_ERROR);
        } catch(Exception e){
            throw new Exception(e);
        }

    }



}
