package com.likelion.cheg.init;

import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.stock.Stock;
import com.likelion.cheg.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class initDB {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInitCategory();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final CategoryService categoryService;
        private final ProductRepository productRepository;

        public void dbInitCategory(){

            Category category1 = categoryService.saveCategory("신발");
            Category category2 = categoryService.saveCategory("상의");
            Category category3 = categoryService.saveCategory("하의");
            Category category4 = categoryService.saveCategory("악세사리");

            Stock stock1 = Stock.builder().quantity(10).build();
            Stock stock2 = Stock.builder().quantity(10).build();
            Stock stock3 = Stock.builder().quantity(10).build();
            Stock stock4 = Stock.builder().quantity(10).build();
            Stock stock5 = Stock.builder().quantity(10).build();
            Stock stock6 = Stock.builder().quantity(10).build();
            Stock stock7 = Stock.builder().quantity(10).build();
            Stock stock8 = Stock.builder().quantity(10).build();
            Stock stock9 = Stock.builder().quantity(10).build();
            Stock stock10 = Stock.builder().quantity(10).build();
            Stock stock11 = Stock.builder().quantity(10).build();
            Stock stock12 = Stock.builder().quantity(10).build();
            Stock stock13 = Stock.builder().quantity(10).build();
            Stock stock14 = Stock.builder().quantity(10).build();

            Product product1 = Product.createProduct(category1,"Dior","디올 부츠","디올 신상 부츠입니다.",500,"부츠.avif",stock1);
            Product product2 = Product.createProduct(category1,"Gucci","구찌 운동화","구찌 신상 운동화입니다.",2000,"구찌신발.avif",stock2);
            Product product3 = Product.createProduct(category1,"Dior","디올 단화","디올 신상 단화입니다.",10000,"단화.webp",stock3);
            Product product4 = Product.createProduct(category2,"Polo","폴로 니트","폴로 신상 니트입니다.",200,"니트.jpg",stock4);
            Product product5 = Product.createProduct(category2,"Polo","폴로 스웨터","폴로 신상 스웨터입니다.",500,"스웨터.webp",stock5);
            Product product6 = Product.createProduct(category2,"Musinsa","무텐다드 셔츠","무신사 신상 셔츠입니다.",1000,"셔츠.jpg",stock6);
            Product product7 = Product.createProduct(category3,"Musinsa","무탠다드 슬랙스","무신사 슬랙스입니다",200,"슬랙스.jpg",stock7);
            Product product8 = Product.createProduct(category3,"Branded","브랜디드 청바지","브랜디드 신상 청바지입니다.",500,"청바지.jpg",stock8);
            Product product9 = Product.createProduct(category3,"Beslow","비슬로우 반바지","비슬로우 신상 반바지입니다.",1000,"반바지.jpg",stock9);
            Product product10 = Product.createProduct(category4,"Chrome Hearts","크롬하츠 귀걸이","크롬하츠 신상 귀걸이입니다.",1000,"귀걸이.png",stock10);
            Product product11 = Product.createProduct(category4,"Louis Vuitton","루이비똥 목걸이","루이비똥 신상 목걸이입니다.",1000,"목걸이.webp",stock11);
            Product product12 = Product.createProduct(category4,"Chrome Hearts","크롬하츠 반지","크롬하츠 신상 반지입니다",200,"반지.jpeg",stock12);
            Product product13 = Product.createProduct(category4,"Rolex","롤렉스 시계","롤렉스 시계입니다.",500,"시계.jpg",stock13);
            Product product14 = Product.createProduct(category4,"Nadia","나디아 팔찌","나디아 신상 팔찌입니다.",1000,"팔찌.webp",stock14);

            List<Product> productList = Arrays.asList(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11, product12, product13, product14);
            productRepository.saveAll(productList);
        }
    }
}
