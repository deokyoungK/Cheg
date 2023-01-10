package com.likelion.cheg.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.likelion.cheg.domain.delivery.Delivery;
import com.likelion.cheg.domain.orderItem.OrderItem;
import com.likelion.cheg.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
    @JsonIgnoreProperties({"order"})
    private List<OrderItem> orderItemList;

    @OneToOne
    @JsonIgnoreProperties({"order"})
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private int order_status;
    private String order_number;

    private LocalDateTime createDate;

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
