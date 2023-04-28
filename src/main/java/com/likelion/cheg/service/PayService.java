package com.likelion.cheg.service;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.web.dto.pay.PayCartResponseDto;
import com.likelion.cheg.web.dto.pay.PayDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PayService {

    //DetailPayment페이지로 price(총금액), product(상품정보) 묶어서 DTO로 보내기
    public PayDetailResponseDto makeDetailResponseDto(Product product, int amount){
        int totalPrice = product.getPrice() * amount; //총금액 계산

        PayDetailResponseDto payDetailResponseDto = new PayDetailResponseDto(
                product.getId(),
                product.getUrl(),
                product.getBrand(),
                product.getName(),
                product.getPrice(),
                amount,
                totalPrice
        );
        return payDetailResponseDto;
    }

    //장바구니 결제 페이지에 필요한 DTO, 값들 Map으로 묶어서 생성(VIEW)
    public Map<String, Object> makeCartResponseDto(List<Cart> cartList){
        Map<String, Object> values = new HashMap<>();

        int cartListTotalPrice = 0;
        int amount = 0;
        String name = "";
        List<PayCartResponseDto> payCartResponseDtos = new ArrayList<>();

        for(Cart cart : cartList){
            cartListTotalPrice += cart.getCartTotalPrice();
            amount += cart.getProductCount();
            name += cart.getProduct().getName();

            PayCartResponseDto payCartResponseDto = new PayCartResponseDto();
            payCartResponseDto.setProductUrl(cart.getProduct().getUrl());
            payCartResponseDto.setProductBrand(cart.getProduct().getBrand());
            payCartResponseDto.setProductName(cart.getProduct().getName());
            payCartResponseDto.setProductCount(cart.getProductCount());
            payCartResponseDto.setCartTotalPrice(cart.getCartTotalPrice());
            payCartResponseDtos.add(payCartResponseDto);
        }
        values.put("list",payCartResponseDtos);
        values.put("cartListTotalPrice",cartListTotalPrice);
        values.put("amount",amount);
        values.put("name",name);

        return values;
    }

}
