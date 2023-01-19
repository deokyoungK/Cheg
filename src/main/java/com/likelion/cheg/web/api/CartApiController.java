package com.likelion.cheg.web.api;

import com.likelion.cheg.config.auth.PrincipalDetail;
import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.service.CartService;
import com.likelion.cheg.web.dto.CMResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class CartApiController {

    private final CartService cartService;
    @PostMapping("api/cart/{productId}/{amount}")
    public ResponseEntity<?> addCart(@PathVariable int productId, @PathVariable int amount, @AuthenticationPrincipal PrincipalDetail principalDetail){
        cartService.addCart(principalDetail.getUser(),productId,amount);
        return new ResponseEntity<>(new CMResponseDto<>(1,"장바구니 추가 성공", ""), HttpStatus.OK);

    }

    @PostMapping("api/cart/{cartId}/down")
    public ResponseEntity<?> downCart(@PathVariable int cartId, @AuthenticationPrincipal PrincipalDetail principalDetail){
        Cart cart = cartService.downCart(cartId);
        List<Cart> cartList = cartService.loadCart(principalDetail.getUser().getId());
        return new ResponseEntity<>(new CMResponseDto<>(1,"장바구니 수량감소 성공",cartList), HttpStatus.OK);
    }

    @PostMapping("api/cart/{cartId}/up")
    public ResponseEntity<?> upCart(@PathVariable int cartId, @AuthenticationPrincipal PrincipalDetail principalDetail){
        Cart cart = cartService.upCart(cartId);
        List<Cart> cartList = cartService.loadCart(principalDetail.getUser().getId());
        return new ResponseEntity<>(new CMResponseDto<>(1,"장바구니 수량증가 성공",cartList), HttpStatus.OK);
    }

    @PostMapping("api/cart/{cartId}/delete")
    public ResponseEntity<?> deleteCart(@PathVariable int cartId, @AuthenticationPrincipal PrincipalDetail principalDetail){
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(new CMResponseDto<>(1,"장바구니 수량증가 성공",""), HttpStatus.OK);
    }

}
