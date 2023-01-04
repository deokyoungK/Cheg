package com.likelion.cheg.web.controller;


import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.handler.ex.CustomValidationException;
import com.likelion.cheg.service.AuthService;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;
    private final ProductService productService;

    @GetMapping("/")
    public String home(Model model){
        List<Product> productList = productService.loadAllProducts();
        model.addAttribute("productList",productList);
        return "layout/home";
    }

    @GetMapping("/auth/login")
    public String loginForm(){
        return "auth/login";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("회원가입 유효성 검사 실패",errorMap);
        }else{
            User user = signupDto.toEntity();
            User userEntity = authService.signup(user);
            return "auth/login";
        }


    }
}
