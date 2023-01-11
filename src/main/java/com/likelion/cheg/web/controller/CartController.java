package com.likelion.cheg.web.controller;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.*;
@RequiredArgsConstructor
@Controller
public class CartController {

    private final CartService cartService;
    @GetMapping("/cart/{id}")
    public String goCart(@PathVariable int id, Model model){
        List<Cart> cartList = cartService.loadCart(id);
        int price=0;
        for(Cart cart : cartList){
            price += cart.getTotal_price();
        }
        model.addAttribute("carts",cartList);
        model.addAttribute("price",price);
        return "user/cart";
    }



}
