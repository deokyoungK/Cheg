package com.likelion.cheg.web.controller;

import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ex.CustomException;
import com.likelion.cheg.handler.ex.CustomValidationApiException;
import com.likelion.cheg.handler.ex.CustomValidationException;
import com.likelion.cheg.service.AuthService;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.service.UserService;
import com.likelion.cheg.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;
    private final UserService userService;


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

        //중복아이디체크
        authService.uniqueUsernameCheck(signupDto.getUsername());

        //@Valid 유효성검사
        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("회원가입 유효성 검사 실패",errorMap);
        }else{
            User user = authService.signup(signupDto);
            return "auth/login";
        }


    }
}
