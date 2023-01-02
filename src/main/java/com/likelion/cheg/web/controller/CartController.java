package com.likelion.cheg.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CartController {

    @GetMapping("/cart")
    public String enterCart(){
        return "user/cart";
    }


}
