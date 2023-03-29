package com.likelion.cheg.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUser_Id(int id);

    //userid, productid로 조회
    @Query(value="SELECT * FROM cart c WHERE c.user_id = :userId AND c.product_id = :productId",nativeQuery = true)
    Cart findByUserIdAndProductId(@Param("userId") int userId, @Param("productId") int productId);

    //userid로 조회
    @Query(value="SELECT * FROM cart c WHERE c.user_id = :userId",nativeQuery = true)
    List<Cart> loadCartByUserId(@Param("userId") int userId);

    //cartid로 삭제
    @Query(value="DELETE FROM cart c WHERE c.id = :cartId",nativeQuery = true)
    void deleteByCartId(@Param("cartId") int cartId);

}
