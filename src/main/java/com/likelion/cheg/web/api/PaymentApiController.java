package com.likelion.cheg.web.api;

import com.likelion.cheg.config.auth.PrincipalDetail;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.dto.CMResponseDto;
import com.likelion.cheg.service.CartService;
import com.likelion.cheg.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaymentApiController {

    @PostMapping("api/payment/{productId}/{amount}")
    public ResponseEntity<?> detailToPayment(@PathVariable int productId, @PathVariable int amount){
        return new ResponseEntity<>(new CMResponseDto<>(1,"성공", new int[]{productId, amount}), HttpStatus.OK);
    }



}
