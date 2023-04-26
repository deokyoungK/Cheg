package com.likelion.cheg.web.controller;

import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.service.ProductService;
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

    @GetMapping("/")
    public String home(Model model){
        List<Product> productList = productService.loadProductsDESC();
        model.addAttribute("productList",productList);
        return "layout/home";
    }

    @GetMapping("/detail/{productId}")
    public String getDetail(@PathVariable int productId, Model model){
        Product product = productService.loadProduct(productId);
        model.addAttribute("product",product);
        return "product/detail";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model){
        List<Product> productList = productService.searchProductByKeyword(keyword);
        model.addAttribute("productList",productList);

        return "layout/home";
    }

    @GetMapping("/category/{name}")
    public String category(@PathVariable String name, Model model){
        List<Product> productList = productService.searchProductByCategory(name);
        model.addAttribute("productList",productList);
        return "layout/home";
    }





}
