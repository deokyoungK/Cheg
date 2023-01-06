package com.likelion.cheg.domain.cart;

import com.likelion.cheg.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUser_Id(int id);

    //userid, productid로 조회
    @Query(value="SELECT * FROM Cart c WHERE c.user_id = :userId AND c.product_id = :productId",nativeQuery = true)
    Cart findByUserIdAndProductId(@Param("userId") int userId, @Param("productId") int productId);

    //userid로 조회
    @Query(value="SELECT * FROM CART c WHERE c.user_id = :userId",nativeQuery = true)
    List<Cart> loadCartByUserId(@Param("userId") int userId);
}
