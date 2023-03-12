package com.likelion.cheg.domain.product;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.category.CategoryRepository;
import com.likelion.cheg.web.dto.product.ProductUploadDto;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@ToString(exclude = {"cart","category"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference //순환참조 방지
    @ManyToOne
    @JoinColumn(name="category")
    private Category category; //상품 카테고리

    private String url; //상품_이미지 경로
    private String brand; //상품 브랜드
    private String name; //상품명
    private int price; //상품가격
    private String description; //상품설명
    private LocalDateTime createDate; //날짜

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
    //상품 생성 메서드
    public static Product createProduct(Category category, String imageFileName, ProductUploadDto productUploadDto){

        Product product = new Product();
        product.setCategory(category);
        product.setUrl(imageFileName);
        product.setBrand(productUploadDto.getBrand());
        product.setName(productUploadDto.getName());
        product.setDescription(productUploadDto.getDescription());
        product.setPrice(Integer.parseInt(productUploadDto.getPrice()));

        return product;
    }




}
