package com.likelion.cheg.service;

import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.handler.ex.CustomException;
import com.likelion.cheg.handler.ex.CustomValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

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
