package com.likelion.cheg.web.controller;

import com.likelion.cheg.service.AuthService;
import com.likelion.cheg.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    @GetMapping("/auth/login")
    public String loginForm(){
        return "auth/login";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

}
