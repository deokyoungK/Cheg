package com.likelion.cheg.domain.delivery;

import com.likelion.cheg.domain.enumType.DeliveryStatus;
import com.likelion.cheg.domain.order.Order;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "DELIVERY")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="order_id")
    private Order order; //주문

    private String deliveryAddress; //배송주소

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus deliveryStatus; //배송상태("배송전","배송준비","배송완료")

    private LocalDateTime createDate; //날짜

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    //변경 필요
    public void setOrder(Order order){
        this.order = order;
    }

    //Delivery 생성 메서드
    public static Delivery createDelivery(String deliveryAddress, DeliveryStatus deliveryStatus){
        Delivery delivery = Delivery.builder()
                .deliveryAddress(deliveryAddress)
                .deliveryStatus(deliveryStatus.배송전)
                .build();
        return delivery;
    }
}
