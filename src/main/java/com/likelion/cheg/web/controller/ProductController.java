package com.likelion.cheg.web.controller;

import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.handler.ex.CustomException;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.web.dto.product.ProductDetailResponseDto;
import com.likelion.cheg.web.dto.product.ProductHomeResponseDto;
import com.likelion.cheg.web.dto.product.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/")
    public String home(Model model){
        List<Product> productList = productRepository.findAllDesc();
        List<ProductHomeResponseDto> productListDto = productService.makeHomeResponseDto(productList);
        model.addAttribute("productListDto",productListDto);
        return "layout/home";
    }

    @GetMapping("/detail/{productId}")
    public String getDetail(@PathVariable int productId, Model model){
        Product product = productRepository.findById(productId).orElseThrow(()->{
            return new CustomException("상품을 찾을 수 없습니다.");
        });
        ProductDetailResponseDto productDto = productService.makeDetailResponseDto(product);
        model.addAttribute("productDto",productDto);
        return "product/detail";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model){
        List<Product> productList = productRepository.searchByKeyword(keyword);
        List<ProductHomeResponseDto> productListDto = productService.makeHomeResponseDto(productList);
        model.addAttribute("productListDto",productListDto);
        return "layout/home";
    }

    @GetMapping("/category/{name}")
    public String category(@PathVariable String name, Model model){
        List<Product> productList = productService.searchProductByCategory(name);
        List<ProductHomeResponseDto> productListDto = productService.makeHomeResponseDto(productList);
        model.addAttribute("productListDto",productListDto);
        return "layout/home";
    }

}
