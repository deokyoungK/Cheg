package com.likelion.cheg.service;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.handler.ex.CustomException;
import com.likelion.cheg.web.dto.cart.AddCartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public List<Cart> loadCart(int userId){
        List<Cart> cartList = cartRepository.loadCartByUserId(userId);
        //cart마다 총가격 넣어주기.
        for(Cart cart:cartList){
            cart.calculateTotalPrice();
        }
        return cartList;
    }

    @Transactional
    public Cart addCart(User user, AddCartDto addCartDto){
        //cart 찾기
        Cart cart = cartRepository.findByUserIdAndProductId(user.getId(),addCartDto.getProductId());

        //새로 만든 cart라면 장바구니 생성
        if(cart == null){
            Product product = productRepository.findById(addCartDto.getProductId()).orElseThrow(()->{
                return new CustomException("상품을 찾을 수 없습니다.");
            });

            Cart newCart = Cart.createCart(user,product,addCartDto.getProductCount());
            cartRepository.save(newCart);

            return newCart;

        }else{ //원래 있다면 수량 더하기
            cart.changeCount(cart.getProductCount()+addCartDto.getProductCount());
            return cart;
        }
    }

    @Transactional
    public Cart downCart(int cartId){
        Cart cart = cartRepository.findById(cartId).orElseThrow(()->{
            return new CustomException("찾을 수 없는 장바구니 입니다.");
        });

        //수량 0개가 아닐때 감소시킴.
        if(cart.getProductCount() != 0) {
            cart.changeCount(-1);
        }
        return cart;
    }

    @Transactional
    public Cart upCart(int cartId){
        Cart cart = cartRepository.findById(cartId).orElseThrow(()->{
            return new CustomException("찾을 수 없는 장바구니 입니다.");
        });

        cart.changeCount(1);
        return cart;
    }

    @Transactional
    public void deleteCart(User user, int cartId){

        Cart cart = cartRepository.findById(cartId).orElseThrow(()->{
            return new CustomException("찾을 수 없는 장바구니입니다.");
        });
        user.getCarts().remove(cart);
        cartRepository.delete(cart);

    }
}
