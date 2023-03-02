package com.likelion.cheg.domain.orderItem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="order_id")
    private Order order;

    private int quantity;

    private int total_price;

    private LocalDateTime createDate;

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    //주문상품 생성 메서드
    public static OrderItem createOrderItem(Product product, int product_price, int quantity){
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setTotal_price(product_price * quantity);
        orderItem.setQuantity(quantity);
        return orderItem;
    }

}
