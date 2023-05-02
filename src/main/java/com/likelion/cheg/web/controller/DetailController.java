package com.likelion.cheg.web.controller;

import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.handler.ex.CustomBusinessException;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.web.dto.product.ProductDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class DetailController {

    private final ProductService productService;
    private final ProductRepository productRepository;


    @GetMapping("/detail/{productId}")
    public String getDetail(@PathVariable int productId, Model model){
        Product product = productRepository.findById(productId).orElseThrow(()->{
            return new CustomBusinessException("상품을 찾을 수 없습니다.");
        });
        ProductDetailResponseDto productDto = productService.makeDetailResponseDto(product);
        model.addAttribute("productDto",productDto);
        return "product/detail";
    }

}
