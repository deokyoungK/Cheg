package com.likelion.cheg.service;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.handler.ex.CustomBusinessException;
import com.likelion.cheg.web.dto.cart.AddCartDto;
import com.likelion.cheg.web.dto.cart.CartApiResponseDto;
import com.likelion.cheg.web.dto.cart.CartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    //장바구니 페이지에 필요한 DTO, 값 Map으로 묶어서 생성(VIEW)
    public Map<String, Object> makeCartDto(List<Cart> cartList){
        Map<String, Object> values = new HashMap<>();
        int cartListTotalPrice = 0;
        List<CartResponseDto> cartResponseDtos = new ArrayList<>();

        for(Cart cart : cartList){
            cartListTotalPrice += cart.getCartTotalPrice();

            CartResponseDto cartResponseDto = new CartResponseDto();
            cartResponseDto.setId(cart.getId());
            cartResponseDto.setCartTotalPrice(cart.getCartTotalPrice());
            cartResponseDto.setProductCount(cart.getProductCount());
            cartResponseDto.setProductUrl(cart.getProduct().getUrl());
            cartResponseDto.setProductName(cart.getProduct().getName());

            cartResponseDtos.add(cartResponseDto);
        }
        values.put("list",cartResponseDtos);
        values.put("cartListTotalPrice",cartListTotalPrice);

        return values;
    }


    //장바구니 수량 변경 시 화면 렌더링에 필요한 DTO 생성(API)
    @Transactional
    public List<CartApiResponseDto> makeApiResponseDto(List<Cart> cartList){
        List<CartApiResponseDto> cartApiResponseDtos = new ArrayList<>();
        for(Cart cart : cartList){
            CartApiResponseDto cartApiResponseDto = new CartApiResponseDto();
            cartApiResponseDto.setCartId(cart.getId());
            cartApiResponseDto.setProductCount(cart.getProductCount());
            cartApiResponseDto.setCartTotalPrice(cart.getCartTotalPrice());

            cartApiResponseDtos.add(cartApiResponseDto);
        }
        return cartApiResponseDtos;
    }

    @Transactional
    public List<Cart> loadCart(int userId){
        List<Cart> cartList = cartRepository.loadCartByUserId(userId);
        //cart마다 총가격 넣어주기
        for(Cart cart : cartList){
            cart.calculateTotalPrice();
        }
        return cartList;
    }

    @Transactional
    public Cart addCart(int userId, AddCartDto addCartDto){
        User user = userRepository.findById(userId).orElseThrow(()->{
            return new CustomBusinessApiException(ErrorCode.NOT_FOUND_USER);
        });
        Cart cart = cartRepository.findByUserIdAndProductId(userId,addCartDto.getProductId());
        Product product = productRepository.findById(addCartDto.getProductId()).orElseThrow(()->{
            return new CustomBusinessApiException(ErrorCode.NOT_FOUND_PRODUCT);
        });

        if(user.getCarts().contains(cart)){ //이미 있으면
            int totalCount = addCartDto.getProductCount() + cart.getProductCount();
            //현재 있는 장바구니의 수량과 DTO의 수량의 합이 재고를 넘으면 상품 재고만큼 카운트 변경
            if(totalCount > product.getStockQuantity()){
                cart.changeCount(product.getStockQuantity());
            }else{
                cart.changeCount(cart.getProductCount() + addCartDto.getProductCount());
            }
            return cart;
        }else{
            Cart newCart = Cart.createCart(user,product,addCartDto.getProductCount());
            cartRepository.save(newCart);
            return newCart;
        }
    }

    @Transactional
    public Cart downCart(int cartId){
        Cart cart = cartRepository.findById(cartId).orElseThrow(()->{
            return new CustomBusinessApiException(ErrorCode.NOT_FOUND_CART);
        });

        //수량 1개 이하인 경우에는 감소할 수 없음.
        if(cart.getProductCount() <= 1) {
            throw new CustomBusinessApiException(ErrorCode.FAIL_DECREASE_AMOUNT);
        }

        //장바구니 수량 감소
        cart.changeCount(cart.getProductCount()-1);

        return cart;
    }

    @Transactional
    public Cart upCart(int cartId){
        Cart cart = cartRepository.findById(cartId).orElseThrow(()->{
            return new CustomBusinessApiException(ErrorCode.NOT_FOUND_CART);
        });

        Product product = cart.getProduct();
        int newCount = cart.getProductCount() + 1;

        //재고 수량확인
        if(newCount > product.getStockQuantity()){
            throw new CustomBusinessApiException(ErrorCode.EXCEED_PRODUCT_STOCK);
        }

        //장바구니 수량 증가
        cart.changeCount(cart.getProductCount()+1);

        return cart;
    }

    @Transactional
    public void deleteCart(int userId, int cartId){
        User user = userRepository.findById(userId).orElseThrow(()->{
            return new CustomBusinessApiException(ErrorCode.NOT_FOUND_USER);
        });
        Cart cart = cartRepository.findById(cartId).orElseThrow(()->{
            return new CustomBusinessApiException(ErrorCode.NOT_FOUND_CART);
        });

        user.getCarts().remove(cart);
        cartRepository.delete(cart);
    }
}
