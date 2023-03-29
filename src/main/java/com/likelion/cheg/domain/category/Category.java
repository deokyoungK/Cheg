package com.likelion.cheg.domain.category;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.likelion.cheg.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    //mappedBy="category" --> 연관관계의 주인이 아님
    @JsonBackReference //순환참조 방지
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<Product>();


    private LocalDateTime createDate;

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    public Category(String name){
        this.name = name;
    }

    public static Category createCategory(String name){
        Category category = new Category();
        category.setName(name);
        return category;
    }

}
