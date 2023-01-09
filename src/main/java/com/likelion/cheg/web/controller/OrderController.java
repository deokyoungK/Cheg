package com.likelion.cheg.web.controller;

import com.likelion.cheg.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/payment/{userId}/{route}")
    public String payment(@PathVariable int userId, @PathVariable int route){
        if(route==0){
            //장바구니
            System.out.println("장바구니에서 옴");
        }else{
            //상세
            System.out.println("상세에서 옴");
        }

        return "user/payment";
    }



}
