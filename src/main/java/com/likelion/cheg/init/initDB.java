package com.likelion.cheg.init;

import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

            Product product1 = Product.createProduct(category1,"Dior","디올신발","명품 디올 신발입니다.",1000,"",100);
            Product product2 = Product.createProduct(category2,"Covernat","반팔티","커버낫 반팔티에요~",2000,"",100);
            Product product3 = Product.createProduct(category3,"Polo","신제품 폴로티","신제품입니다, 많이 사세요",10000,"",3);
            Product product4 = Product.createProduct(category4,"Zara","자라목","자라목입니다",200,"",10);
            Product product5 = Product.createProduct(category1,"Polo","폴로신발","폴로 신상 신발입니다.",500,"",100);
            Product product6 = Product.createProduct(category2,"Emart","이마트스웨터","봄 신상입니다.",1000,"",100);

            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);
            productRepository.save(product6);
        }




    }

}
