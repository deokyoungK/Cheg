package com.likelion.cheg.web.controller;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.handler.ex.CustomException;
import com.likelion.cheg.service.CartService;
import com.likelion.cheg.service.PayService;
import com.likelion.cheg.web.dto.pay.PayCartResponseDto;
import com.likelion.cheg.web.dto.pay.PayDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.*;
@RequiredArgsConstructor
@Controller
public class OrderController {

    private final PayService payService;
    private final CartService cartService;
    private final ProductRepository productRepository;

    @GetMapping("/detailPayment/{productId}/{amount}")
    public String detailPayment(@PathVariable int productId, @PathVariable int amount, Model model){
        Product product = productRepository.findById(productId).orElseThrow(()->{
            return new CustomException("상품을 찾을 수 없습니다.");
        });
        PayDetailResponseDto payDetailResponseDto = payService.makeDetailResponseDto(product,amount);
        model.addAttribute("payDetailDto",payDetailResponseDto);
        return "pay/detailPayment";
    }


    @GetMapping("/cartPayment/{userId}")
    public String CartPayment(@PathVariable int userId, Model model){
        List<Cart> cartList = cartService.loadCart(userId);
        Map<String,Object> responseMap = cartService.makeCartResponseDto(cartList);

//        int price=0;
//        int amount=0;
//        String name = "";
//        for(Cart cart : cartList){
//            price += cart.getCartTotalPrice();
//            amount += cart.getProductCount();
//            name += cart.getProduct().getName();
//        }
//
//        model.addAttribute("name",name);
//        model.addAttribute("amount",amount);
//        model.addAttribute("price",price);
//        model.addAttribute("cartList",cartList);
        model.addAttribute("responseMap",responseMap);
        return "pay/cartPayment";
    }
}