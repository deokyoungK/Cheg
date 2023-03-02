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
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="order_id")
    private Order order;

    private String delivery_address;
    private String delivery_status;
    private LocalDateTime createDate;

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
