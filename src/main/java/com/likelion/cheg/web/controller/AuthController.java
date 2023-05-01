package com.likelion.cheg.web.controller;

import com.likelion.cheg.service.AuthService;
import com.likelion.cheg.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@Controller
public class AuthController {

    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error",required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model){
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "auth/login";
    }
    @GetMapping("/auth/signup")
    public String signup() {
        return "auth/signup";
    }

}
