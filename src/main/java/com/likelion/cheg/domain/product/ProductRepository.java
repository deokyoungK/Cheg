package com.likelion.cheg.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    //모든 상품 내림차순 조회
    @Query(value = "SELECT * FROM PRODUCT p ORDER BY p.id DESC",nativeQuery = true)
    List<Product> findAllDesc();

    //keyword로 상품조회
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.brand LIKE %:keyword%")
    List<Product> findAllByKeyword(String keyword);

    //categoryId로 상품조회(fetch join)
    @Query("SELECT p FROM Product p JOIN FETCH p.category c WHERE c.id = :categoryId ORDER BY p.id DESC")
    List<Product> findAllByCategoryId(int categoryId);
}
