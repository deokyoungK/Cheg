package com.likelion.cheg.web.apiController;

import com.likelion.cheg.config.auth.PrincipalDetail;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.service.OrderService;
import com.likelion.cheg.web.dto.CMResponse;
import com.likelion.cheg.web.dto.delivery.DeliveryDto;
import com.likelion.cheg.web.dto.pay.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderService orderService;
    private final UserRepository userRepository;
    @PostMapping("api/payment/{productId}/{amount}")
    public ResponseEntity<?> detailToPayment(@PathVariable int productId, @PathVariable int amount){
        return new ResponseEntity<>(new CMResponse<>(1,"성공", new int[]{productId, amount}), HttpStatus.OK);
    }

    @PostMapping("api/delivery/validation")
    public ResponseEntity<?> deliveryValidation(@Validated @RequestBody DeliveryDto deliveryDto){
        return new ResponseEntity<>(new CMResponse<>(1,"배송정보 유효성 검사 성공",""), HttpStatus.OK);

    }

    @PostMapping("api/order")
    public ResponseEntity<?> Order(@RequestBody PaymentDto paymentDto, @AuthenticationPrincipal PrincipalDetail principalDetail){
        Order order = orderService.makeOrder(principalDetail.getUser().getId(),paymentDto);
        User user = userRepository.findById(principalDetail.getUser().getId()).orElseThrow(()->{
            return new CustomBusinessApiException(ErrorCode.NOT_FOUND_USER);
        });
        principalDetail.setUser(user); //세션정보 변경
        return new ResponseEntity<>(new CMResponse<>(1,"주문 성공",principalDetail.getUser().getId()), HttpStatus.OK);
    }
}
