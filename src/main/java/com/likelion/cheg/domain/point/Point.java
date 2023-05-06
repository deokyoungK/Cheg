package com.likelion.cheg.domain.point;

import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "POINT")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;

    @OneToOne
    @JoinColumn(name = "user_id") //연관관계의 주인
    private User user;

    private LocalDateTime createDate; //날짜

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    //Point 생성 메서드
    public static Point createPoint(int amount){
        return Point.builder()
                .amount(amount)
                .build();
    }
    //변경필요
    public void setUser(User user){
        this.user = user;
    }

    public void changePoint(int amount){
        this.amount = amount;
    }
}
