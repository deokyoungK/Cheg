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

    @GetMapping("/cart/{userId}")
    public String goCart(@PathVariable int userId, Model model){
        List<Cart> cartList = cartService.loadCart(userId);
        Map<String, Object> responseMap = cartService.makeCartDto(cartList);
//        int price=0;
//        for(Cart cart : cartList){
//            price += cart.getCartTotalPrice();
//        }
//
//        model.addAttribute("carts",cartList);
//        model.addAttribute("price",price);
//
        model.addAttribute("responseMap",responseMap);
        return "user/cart";
    }
}
