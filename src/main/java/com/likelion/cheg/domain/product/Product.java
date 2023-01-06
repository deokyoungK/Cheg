package com.likelion.cheg.domain.product;


import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.category.Category;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@ToString(exclude = "cart")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //양방향 관계를 맺었지만 영속성 컨텍스트에서 카테고리가 상품의 정보를 알기 위해서는
    //product.getCategory().getProducts().add(product)와 같이
    //카테고리에도 product를 넣어주어야 한다.
    @ManyToOne
    @JoinColumn(name="category")
    private Category category;

    private String url;
    private String brand;
    private String name;
    private int price;
    private String description;
    private LocalDateTime createDate;

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    //카테고리 변경
    public void setCategory(Category category){
        //이미 카테고리가 있을 경우 관계 제거
        if(this.category != null){
            this.category.getProducts().remove(this);
        }
        this.category = category;
        if(category != null){
            category.getProducts().add(this);
        }
    }


}
