package com.likelion.cheg.cart;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.service.CartService;
import com.likelion.cheg.web.dto.cart.AddCartDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateCartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;

    @Test
    public void 장바구니추가_새로운상품_테스트_성공() {
        //given
        int userId = 1;
        int productId = 1;
        //user세팅
        User user = User.builder()
                .id(userId)
                .build();
        //product세팅
        Product product = Product.builder()
                .id(productId)
                .build();
        //DTO세팅(상품id,상품갯수)
        AddCartDto addCartDto = new AddCartDto(productId, 3);
        //cart세팅(처음에 null)
        Cart cart = null;

        //when
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserIdAndProductId(userId, productId)).thenReturn(cart);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Cart cartEntity = cartService.addCart(userId, addCartDto);

        // then
        assertNotNull(cartEntity);
        assertEquals("상품이 맞는지 확인",cartEntity.getProduct(),product);
        assertEquals("수량이 맞는지 확인",cartEntity.getProductCount(),3);
        assertEquals("user와 연관관계가 올바르게 매핑됐는지",cartEntity.getUser(),user);
    }
    @Test
    public void 장바구니추가_새로운상품_테스트_실패() {
        //given
        int userId = 1;
        int productId = 1;
        //user세팅
        User user = User.builder()
                .id(userId)
                .build();

        //product세팅
        Product product = Product.builder()
                .id(productId)
                .build();

        //DTO세팅(상품id,상품갯수)
        AddCartDto addCartDto = new AddCartDto(productId, 3);

        //cart세팅(처음에 null)
        Cart cart = null;

        //when
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserIdAndProductId(userId, productId)).thenReturn(cart);
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        Throwable e = assertThrows(CustomBusinessApiException.class,
                () -> cartService.addCart(userId, addCartDto));

        // then
        assertEquals(ErrorCode.NOT_FOUND_PRODUCT, ((CustomBusinessApiException) e).getErrorCode());
    }
    @Test
    public void 장바구니추가_이미있는상품_테스트_성공() {
        //given
        int userId = 1;
        int productId = 1;
        int cartId = 1;
        List<Cart> carts = new ArrayList<>();

        //product세팅
        Product product = Product.builder()
                .id(productId)
                .stockQuantity(5)
                .build();
        //DTO세팅(상품id,상품갯수)
        AddCartDto addCartDto = new AddCartDto(productId, 1);
        //cart세팅
        Cart cart = Cart.builder()
                .id(cartId)
                .product(product)
                .productCount(2)
                .build();
        carts.add(cart);
        //user세팅(cart와 매핑)
        User user = User.builder()
                .id(userId)
                .carts(carts)
                .build();

        //when
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserIdAndProductId(userId, productId)).thenReturn(cart);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Cart cartEntity = cartService.addCart(userId, addCartDto);

        // then
        assertNotNull(cartEntity);
        assertEquals("상품이 맞는지 확인",cartEntity.getProduct(),product);
        assertEquals("수량이 맞는지 확인",cartEntity.getProductCount(),3);
        assertEquals("user의 장바구니 갯수가 그대로인지 확인",user.getCarts().size(),1);
    }

    @Test
    public void 장바구니추가_이미있는상품_테스트_실패() {
        //given
        int userId = 1;
        int productId = 1;
        int cartId = 1;
        List<Cart> carts = new ArrayList<>();

        //product세팅
        Product product = Product.builder()
                .id(productId)
                .build();

        //DTO세팅(상품id,상품갯수)
        AddCartDto addCartDto = new AddCartDto(productId, 1);

        //cart세팅
        Cart cart = Cart.builder()
                .id(cartId)
                .product(product)
                .productCount(2)
                .build();
        carts.add(cart);

        //user세팅(cart와 매핑)
        User user = User.builder()
                .id(userId)
                .carts(carts)
                .build();

        //when
        when(userRepository.findById(userId)).thenReturn(Optional.empty()); //user가 존재 X
        Throwable e = assertThrows(CustomBusinessApiException.class,
                () -> cartService.addCart(userId, addCartDto));

        // then
        assertEquals(ErrorCode.NOT_FOUND_USER, ((CustomBusinessApiException) e).getErrorCode());

    }

}
