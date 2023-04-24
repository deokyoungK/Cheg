package com.likelion.cheg.web.controller;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.service.CartService;
import com.likelion.cheg.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.*;
@RequiredArgsConstructor
@Controller
public class OrderController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/detailPayment/{productId}/{amount}")
    public String detailPayment(@PathVariable int productId, @PathVariable int amount, Model model){
        Product product = productService.loadProduct(productId);
        int price = product.getPrice()*amount;

        model.addAttribute("amount",amount);
        model.addAttribute("product",product);
        model.addAttribute("price",price);
        return "user/detailPayment";
    }

    @GetMapping("/cartPayment/{userId}")
    public String CartPayment(@PathVariable int userId, Model model){
        List<Cart> cartList = cartService.loadCart(userId);

        int price=0;
        int amount=0;
        String name = "";
        for(Cart cart : cartList){
            price += cart.getCartTotalPrice();
            amount += cart.getProductCount();
            name += cart.getProduct().getName();
        }

        model.addAttribute("name",name);
        model.addAttribute("amount",amount);
        model.addAttribute("price",price);
        model.addAttribute("cartList",cartList);
        return "user/cartPayment";
    }
}
