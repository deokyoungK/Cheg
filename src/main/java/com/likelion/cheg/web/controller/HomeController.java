package com.likelion.cheg.web.controller;

import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.category.CategoryRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.service.CategoryService;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.web.dto.category.CategoryResponseDto;
import com.likelion.cheg.web.dto.product.ProductHomeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @GetMapping("/")
    public String home(Model model){
        //상품은 수량만 렌더링
        Long count = productRepository.count();

        //카테고리는 전부 렌더링
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponseDto> categoryListDto = categoryService.makeResponseDto(categoryList);

        model.addAttribute("count",count);
        model.addAttribute("categoryListDto",categoryListDto);
        return "layout/home";
    }

}
