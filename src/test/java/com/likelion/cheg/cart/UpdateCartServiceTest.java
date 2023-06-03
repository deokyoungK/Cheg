package com.likelion.cheg.cart;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.stock.Stock;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.service.CartService;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateCartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Test
    public void 장바구니수량감소_테스트_성공(){
        //given
        int cartId = 1;
        int productCount = 3;
        int productId = 1;
        Product product = Product.builder()
                .id(productId)
                .build();
        Cart cart = Cart.builder()
                .id(cartId)
                .product(product)
                .productCount(productCount)
                .build();
        // when
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        Cart updatedCart = cartService.downCart(cartId);

        // then
        assertEquals("수량이 맞는지 확인",updatedCart.getProductCount(),2);
    }
    @Test
    public void 장바구니수량감소_테스트_실패(){
        // given
        int cartId = 1;

        // when
        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());
        Throwable e = assertThrows(CustomBusinessApiException.class,
                () -> cartService.downCart(cartId));

        // then
        assertEquals(ErrorCode.NOT_FOUND_CART, ((CustomBusinessApiException) e).getErrorCode());

    }
    @Test
    public void 장바구니수량증가_테스트_성공(){
        //given
        int cartId = 1;
        int productCount = 3;
        int productId = 1;

        //stock세팅
        Stock stock = Stock.builder()
                .id(1L)
                .quantity(5).build();

        Product product = Product.builder()
                .id(productId)
                .stock(stock)
                .build();
        Cart cart = Cart.builder()
                .id(cartId)
                .product(product)
                .productCount(productCount)
                .build();

        // when
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        Cart updatedCart = cartService.upCart(cartId);

        // then
        assertEquals("수량이 맞는지 확인",updatedCart.getProductCount(),4);

    }
    @Test
    public void 장바구니수량증가_테스트_실패(){
        // given
        int cartId = 1;

        // when
        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());
        Throwable e = assertThrows(CustomBusinessApiException.class,
                () -> cartService.upCart(cartId));

        // then
        assertEquals(ErrorCode.NOT_FOUND_CART, ((CustomBusinessApiException) e).getErrorCode());
    }





}
