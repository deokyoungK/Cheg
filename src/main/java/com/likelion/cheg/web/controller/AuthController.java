package com.likelion.cheg.web.controller;

import com.likelion.cheg.service.AuthService;
import com.likelion.cheg.service.UserService;
import com.likelion.cheg.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public String signup(@Validated SignupDto signupDto){
        //중복아이디체크
        authService.uniqueUsernameCheck(signupDto.getUsername());
        //회원가입
        authService.signup(signupDto);
        return "auth/login";

    }
}
