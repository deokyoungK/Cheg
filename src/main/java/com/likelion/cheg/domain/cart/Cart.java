package com.likelion.cheg.domain.cart;

import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString(exclude = {"user","product"})
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private int product_count;

    @Transient
    private int total_price;

    //해당 상품에 대한 장바구니 총 가격 계산하기.
    public void calculateTotalPrice(){
         this.total_price = product.getPrice() * product_count;
    }

}
