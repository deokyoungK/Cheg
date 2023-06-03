package com.likelion.cheg.domain.orderItem;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.product.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "ORDERITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="order_id")
    private Order order; //주문

    private int quantity; //주문상품 수량
    private int orderItemTotalPrice; //주문상품 총 금액
    private LocalDateTime createDate; //날짜

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    //변경필요
    public void setOrder(Order order){
        this.order = order;
    }

    //OrderItem 생성 메서드
    public static OrderItem createOrderItem(Product product, int product_price, int quantity){
        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .orderItemTotalPrice(product_price * quantity)
                .quantity(quantity)
                .build();

        //상품재고는 감소해야됨.
        product.getStock().decrease(quantity);
        return orderItem;
    }

}
