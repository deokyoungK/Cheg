package com.likelion.cheg.domain.delivery;

import com.likelion.cheg.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="order_id")
    private Order order; //주문

    private String delivery_address; //배송주소
    private String delivery_status; //배송상태
    private LocalDateTime createDate; //날짜

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    public static Delivery createDelivery(String address, String status){
        Delivery delivery = new Delivery();
        delivery.setDelivery_address(address);
        delivery.setDelivery_status(status);
        return delivery;
    }



}
