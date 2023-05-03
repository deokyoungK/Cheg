package com.likelion.cheg.cart;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.*;
@RunWith(MockitoJUnitRunner.class)
public class DeleteCartServiceTest {

    @InjectMocks
    private CartService cartService;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    public void 장바구니삭제_테스트_성공(){
        //given
        int userId = 1;
        int cartId = 1;
        int productId = 1;
        List<Cart> carts = new ArrayList<>();

        //product세팅
        Product product = Product.builder()
                .id(productId)
                .price(1000)
                .build();

        //cart세팅(2개)
        Cart cart = Cart.builder()
                .id(cartId)
                .product(product)
                .productCount(1)
                .build();
        carts.add(cart);
        Cart cart2 = Cart.builder()
                .id(cartId+1)
                .product(product)
                .productCount(2)
                .build();
        carts.add(cart2);

        //user세팅(cart와 매핑)
        User user = User.builder()
                .id(userId)
                .carts(carts)
                .build();

        //mock 객체 설정
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(cartRepository.findAll()).thenReturn(carts);
        //when
        cartService.deleteCart(userId, cartId);

        //then
        assertEquals("회원의 cart 수량이 맞는지 확인",user.getCarts().size(),1);
        assertEquals("실제 cart 수량이 맞는지 확인",cartRepository.findAll().size(),1);
        verify(cartRepository, times(1)).delete(cart);
    }

    @Test
    public void 장바구니삭제_테스트_실패_회원없음(){
        //given
        int userId = 1;
        int cartId = 1;
        //cart세팅
        Cart cart = Cart.builder()
                .id(cartId)
                .productCount(1)
                .build();

        //when
        when(userRepository.findById(userId)).thenReturn(Optional.empty()); //user가 존재 X
        Throwable e = assertThrows(CustomBusinessApiException.class,
                () -> cartService.deleteCart(userId,cartId));

        // then
        assertEquals(ErrorCode.NOT_FOUND_USER, ((CustomBusinessApiException) e).getErrorCode());
    }
    @Test
    public void 장바구니삭제_테스트_실패_장바구니없음(){
        //given
        int userId = 1;
        int cartId = 1;
        //user세팅
        User user = User.builder()
                .id(userId)
                .build();

        //when
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findById(cartId)).thenReturn(Optional.empty()); //cart가 존재 X
        Throwable e = assertThrows(CustomBusinessApiException.class,
                () -> cartService.deleteCart(userId,cartId));

        // then
        assertEquals(ErrorCode.NOT_FOUND_CART, ((CustomBusinessApiException) e).getErrorCode());
    }
}
