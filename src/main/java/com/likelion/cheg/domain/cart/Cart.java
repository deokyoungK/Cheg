package com.likelion.cheg.domain.cart;

import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.user.User;
import lombok.*;
import org.hibernate.Hibernate;


import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "CART")
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    private int productCount; //장바구니 수

    @Transient
    private int cartTotalPrice; //장바구니 총 금액

    //해당 상품에 대한 장바구니 총 가격 계산
    public void calculateTotalPrice(){
         this.cartTotalPrice = product.getPrice() * productCount;
    }

    //수량 변경
    public void changeCount(int amount){
        this.productCount = productCount + amount;
        this.calculateTotalPrice();
    }

    //Cart 생성 메서드
    public static Cart createCart(User user, Product product, int productCount){
        Cart cart = Cart.builder()
                .user(user)
                .product(product)
                .productCount(productCount)
                .build();

        //연관관계 매핑
        user.getCarts().add(cart);

        //장바구니 총 가격 계산
        cart.calculateTotalPrice();
        return cart;

    }
}
