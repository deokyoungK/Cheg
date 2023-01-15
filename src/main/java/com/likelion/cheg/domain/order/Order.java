package com.likelion.cheg.domain.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.likelion.cheg.domain.delivery.Delivery;
import com.likelion.cheg.domain.orderItem.OrderItem;
import com.likelion.cheg.domain.user.User;
import lombok.*;

import java.util.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderItem> orderItemList;

    @OneToOne
    @JsonIgnoreProperties({"order"})
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private int order_status;
    private String order_number;

    private int order_price;
    private int order_product_count; //상품갯수 -> 마이페이지에서 구분을 위해
    private LocalDateTime createDate;

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
