package com.likelion.cheg.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product p ORDER BY p.id DESC",nativeQuery = true)
    List<Product> findAllDesc();

    @Query(value = "SELECT * FROM product p WHERE p.name LIKE %:keyword% OR p.brand LIKE %:keyword%",nativeQuery = true)
    List<Product> searchByKeyword(String keyword);


}
