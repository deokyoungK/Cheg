package com.likelion.cheg.domain.cart;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString(exclude = {"user","product"})
@Table(name = "cart")
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

    private int product_count; //장바구니 수

    @Transient //컬럼생성 X
    private int total_price; //장바구니 총 가격

    //해당 상품에 대한 장바구니 총 가격 계산하기.
    public void calculateTotalPrice(){
         this.total_price = product.getPrice() * product_count;
    }

}
