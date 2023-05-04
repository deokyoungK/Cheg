package com.likelion.cheg.domain.point;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "POINT")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;

    private LocalDateTime createDate; //날짜

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
