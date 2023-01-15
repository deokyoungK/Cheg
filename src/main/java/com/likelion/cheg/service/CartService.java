package com.likelion.cheg.service;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.handler.ex.CustomException;
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
    public List<Cart> loadCart(int id){
        List<Cart> cartList = cartRepository.loadCartByUserId(id);
        //cart마다 총가격 넣어주기.
        for(Cart cart:cartList){
            cart.calculateTotalPrice();
        }
        return cartList;
    }

    @Transactional
    public void addCart(User user, int productId, int amount){
        Cart cart = cartRepository.findByUserIdAndProductId(user.getId(),productId);

        //새로 만든 cart라면 product_count를 amount로 하는 장바구니 생성
        if(cart == null){
            Product product = productRepository.findById(productId).orElseThrow(()->{
                return new CustomException("상품을 찾을 수 없습니다.");
            });
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setProduct(product);
            newCart.setProduct_count(amount);
            cartRepository.save(newCart);
        }else{ //원래 있다면 product_count + amount해주고 저장
            int prev = cart.getProduct_count();
            cart.setProduct_count(prev+amount);
            cartRepository.save(cart);
        }
    }

    @Transactional
    public Cart downCart(int cartId){
        Cart cart = cartRepository.findById(cartId).orElseThrow(()->{
            return new CustomException("찾을 수 없는 장바구니 입니다.");
        });

        //수량 0개가 아닐때 감소시킴.
        if(cart.getProduct_count() != 0) {
            int prev = cart.getProduct_count();
            cart.setProduct_count(prev - 1);

            //계산을 해주고 commit해야함.
            cart.calculateTotalPrice();
            cartRepository.save(cart);
        }
        return cart;
    }

    @Transactional
    public Cart upCart(int cartId){
        Cart cart = cartRepository.findById(cartId).orElseThrow(()->{
            return new CustomException("찾을 수 없는 장바구니 입니다.");
        });

        int prev = cart.getProduct_count();
        cart.setProduct_count(prev + 1);

        //계산을 해주고 commit해야함.
        cart.calculateTotalPrice();
        cartRepository.save(cart);

        return cart;
    }

    @Transactional
    public void deleteCart(int cartId){
        try{
            cartRepository.deleteById(cartId);
        }catch(Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}
