package com.likelion.cheg.web.api;

import com.likelion.cheg.dto.CMResponseDto;
import com.likelion.cheg.service.OrderService;
import com.likelion.cheg.web.dto.payment.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("api/payment/{productId}/{amount}")
    public ResponseEntity<?> detailToPayment(@PathVariable int productId, @PathVariable int amount){
        return new ResponseEntity<>(new CMResponseDto<>(1,"성공", new int[]{productId, amount}), HttpStatus.OK);
    }


    @PostMapping("api/order")
    public ResponseEntity<?> Order(@RequestBody PaymentDto paymentDto){
        orderService.makeOrder(paymentDto.getUser_id(),paymentDto.getFlag(),paymentDto.getAddress(),paymentDto.getProduct_id(),paymentDto.getAmount());
        return new ResponseEntity<>(new CMResponseDto<>(1,"성공",paymentDto.getAddress()), HttpStatus.OK);
    }


}
