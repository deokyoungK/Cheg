package com.likelion.cheg.service;

import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.web.dto.pay.PayDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayService {

    //DetailPayment페이지로 price(총금액), product(상품정보) 묶어서 DTO로 보내기
    public PayDetailResponseDto makeDetailResponseDto(Product product, int amount){
        int totalPrice = product.getPrice() * amount; //총금액 계산

        PayDetailResponseDto payDetailResponseDto = new PayDetailResponseDto(
                product.getUrl(),
                product.getBrand(),
                product.getName(),
                product.getPrice(),
                amount,
                totalPrice
        );
        return payDetailResponseDto;
    }


}
