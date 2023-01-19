package com.likelion.cheg.web.controller;

import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.category.CategoryRepository;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ex.CustomValidationException;
import com.likelion.cheg.service.CategoryService;
import com.likelion.cheg.service.OrderService;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.service.UserService;
import com.likelion.cheg.web.dto.product.ProductUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
@RequiredArgsConstructor
@Controller
public class AdminController {

    private final ProductService productService;
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final CategoryService categoryService;


    @GetMapping("/admin")
    public String admin(Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList",userList);
        return "admin/admin";
    }

    @GetMapping("/admin/productList")
    public String productList(Model model){
        List<Product> productList = productService.loadAllProducts();
        model.addAttribute("productList",productList);
        return "admin/productList";
    }

    @GetMapping("/admin/orderList")
    public String orderList(Model model){
        List<Order> orderList = orderService.loadAll();
        model.addAttribute("orderList",orderList);
        return "admin/orderList";
    }

    @GetMapping("/admin/addProduct")
    public String addProduct(Model model){
        List<Category> categoryList = categoryService.loadAllCateogory();
        model.addAttribute("categoryList",categoryList);
        return "admin/addProduct";
    }

    @GetMapping("/admin/addCategory")
    public String addCategory(Model model){
        List<Category> categoryList = categoryService.loadAllCateogory();
        model.addAttribute("categoryList",categoryList);
        return "admin/addCategory";
    }

    @PostMapping("/admin/addProduct")
    public String uploadProduct(@Validated ProductUploadDto productUploadDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("상품등록 유효성 검사 실패",errorMap);
        }else{
            //상품등록
            Product product = productService.addProduct(productUploadDto);
            return "redirect:/admin/productList";
        }
    }

    @PostMapping("/admin/addCategory")
    public String addCategory(String category){
        Category newCategory = categoryService.saveOne(category);
        return "redirect:/admin/addCategory";
    }


}
