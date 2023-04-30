package com.likelion.cheg.web.api;

import com.likelion.cheg.config.auth.PrincipalDetail;
import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.service.CartService;
import com.likelion.cheg.web.dto.CMResponse;
import com.likelion.cheg.web.dto.cart.AddCartDto;
import com.likelion.cheg.web.dto.cart.CartApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class CartApiController {

    private final CartService cartService;

    @PostMapping("api/cart/add")
    public ResponseEntity<?> addCart(@RequestBody AddCartDto addCartDto, @AuthenticationPrincipal PrincipalDetail principalDetail){
        cartService.addCart(principalDetail.getUser().getId(),addCartDto);
        return new ResponseEntity<>(new CMResponse<>(1,"장바구니 추가 성공", ""), HttpStatus.OK);
    }

    @PostMapping("api/cart/{cartId}/down")
    public ResponseEntity<?> downCart(@PathVariable int cartId, @AuthenticationPrincipal PrincipalDetail principalDetail){
        cartService.downCart(cartId);
        List<Cart> carts = cartService.loadCart(principalDetail.getUser().getId());
        List<CartApiResponseDto> cartApiResponseDtos = cartService.makeApiResponseDto(carts);
        return new ResponseEntity<>(new CMResponse<>(1,"장바구니 수량감소 성공", cartApiResponseDtos), HttpStatus.OK);
    }

    @PostMapping("api/cart/{cartId}/up")
    public ResponseEntity<?> upCart(@PathVariable int cartId, @AuthenticationPrincipal PrincipalDetail principalDetail){
        cartService.upCart(cartId);
        List<Cart> carts = cartService.loadCart(principalDetail.getUser().getId());
        List<CartApiResponseDto> cartApiResponseDtos = cartService.makeApiResponseDto(carts);
        return new ResponseEntity<>(new CMResponse<>(1,"장바구니 수량증가 성공", cartApiResponseDtos), HttpStatus.OK);
    }

    @PostMapping("api/cart/{cartId}/delete")
    public ResponseEntity<?> deleteCart(@PathVariable int cartId, @AuthenticationPrincipal PrincipalDetail principalDetail){
        cartService.deleteCart(principalDetail.getUser().getId(),cartId);
        return new ResponseEntity<>(new CMResponse<>(1,"장바구니 삭제 성공",""), HttpStatus.OK);
    }

}
