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
    private List<OrderItem> orderItemList = new ArrayList<>();

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

    //연관관계 메서드
    public void setUser(User user){
        this.user = user;
        user.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //주문 생성 메서드
    public static Order createOrder(User user, Delivery delivery, OrderItem... orderItems) {
        //8자리 주문번호 생성
        Random random = new Random();
        String number = Integer.toString(random.nextInt(8)+1);
        for(int i=0;i<7;i++){
            number += Integer.toString(random.nextInt(9));
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrder_number(number.toString());
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setOrder_status(1);

        return order;
    }


    public Order(User user, Delivery delivery, int order_status, String order_number){
        this.user = user;
        this.delivery = delivery;
        this.order_status = order_status;
        this.order_number = order_number;
    }



}
