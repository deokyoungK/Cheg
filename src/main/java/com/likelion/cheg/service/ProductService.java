package com.likelion.cheg.service;

import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.category.CategoryRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.handler.ex.CustomException;
import com.likelion.cheg.web.dto.product.ProductUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void deleteProduct(int productId){
        try{
            productRepository.deleteById(productId);
        }catch(Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Transactional
    public Product addProduct(ProductUploadDto productUploadDto){
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+productUploadDto.getFile().getOriginalFilename();
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        // 통신, I/O -> 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath,productUploadDto.getFile().getBytes());
        }catch(Exception e) {
            e.printStackTrace();
        }

        //상품 저장
        Category category = categoryRepository.findByCategoryName(productUploadDto.getCategory());

        Product product = new Product();
        product.setCategory(category);
        product.setUrl(imageFileName);
        product.setBrand(productUploadDto.getBrand());
        product.setName(productUploadDto.getName());
        product.setDescription(productUploadDto.getDescription());
        product.setPrice(Integer.parseInt(productUploadDto.getPrice()));
        productRepository.save(product);
        return product;
    }

    @Transactional
    public List<Product> loadAllProducts(){
        List<Product> productList = productRepository.findAllDesc();
        return productList;
    }

    @Transactional
    public Product loadProduct(int id){
        Product product = productRepository.findById(id).orElseThrow(()->{
           return new CustomException("상품을 찾을 수 없습니다.");
        });
        return product;
    }

    @Transactional
    public List<Product> searchProductByKeyword(String keyword){
        List<Product> productList = productRepository.searchByKeyword(keyword);
        return productList;
    }

    @Transactional
    public List<Product> searchProductByCategory(String name){
        List<Product> productReturn = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for(Product product : productList){
            if(product.getCategory().getName().equals(name)){
                productReturn.add(product);
            }
        }
        return productReturn;
    }



}
