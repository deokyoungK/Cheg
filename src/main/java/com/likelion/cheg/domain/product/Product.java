package com.likelion.cheg.domain.product;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.orderItem.OrderItem;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.handler.ex.CustomBusinessException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="category")
    private Category category; //상품 카테고리

    private String url; //상품_이미지 경로
    private String brand; //상품 브랜드
    private String name; //상품명
    private int price; //상품가격
    private String description; //상품설명
    private int stockQuantity; //상품 재고
    private LocalDateTime createDate; //날짜

    @PrePersist //db에 insert되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    //재고 감소
    public void dereaseStockQuantity(int amount){
        int updatedStockQuantity = this.stockQuantity - amount;
        if(updatedStockQuantity < 0){
            throw new CustomBusinessApiException(ErrorCode.NOT_ENOUGH_STOCK);
        }
        this.stockQuantity = updatedStockQuantity;
    }


    //카테고리 변경
    public void changeCategory(Category category){
        //이미 카테고리가 있을 경우 관계 제거
        if(this.category != null){
            this.category.getProducts().remove(this);
        }
        this.category = category;
        if(category != null){
            category.getProducts().add(this);
        }
    }

    //상품 생성 메서드
    public static Product createProduct(Category category, String brand, String name, String description, int price, String url, int stockQuantity){
        Product product = Product.builder()
                .category(category)
                .brand(brand)
                .name(name)
                .description(description)
                .price(price)
                .url(url)
                .stockQuantity(stockQuantity)
                .build();
        return product;
    }
}
