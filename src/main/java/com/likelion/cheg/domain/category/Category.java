package com.likelion.cheg.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.likelion.cheg.domain.product.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import static javax.persistence.FetchType.LAZY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "category", fetch = LAZY)
    private List<Product> products = new ArrayList<Product>();

    private LocalDateTime createDate;

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    public Category(String name){
        this.name = name;
    }

    //카테고리 생성 메서드
    public static Category createCategory(String name){
        Category category = Category.builder()
                .name(name)
                .build();
        return category;
    }

}
