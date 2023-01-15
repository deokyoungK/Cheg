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

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    private int quantity;

    private int total_price;

    //해당 상품에 대한 주문상품 총 가격 계산하기.
    public void calculateTotalPrice(){
        this.total_price = product.getPrice() * quantity;
    }

    private LocalDateTime createDate;

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }


}
