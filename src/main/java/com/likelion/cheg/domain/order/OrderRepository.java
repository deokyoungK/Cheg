package com.likelion.cheg.domain.order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    //userid로 조회
    @Query(value="SELECT * FROM orders o WHERE o.member_id = :userId",nativeQuery = true)
    List<Order> loadOrderByUserId(@Param("userId") int userId);

    //주문번호로 검색
    @Query(value="SELECT * FROM orders o WHERE o.orderNumber LIKE %:keyword%",nativeQuery = true)
    List<Order> searchByKeyword(String keyword);

    //userid로 삭제
    @Modifying
    @Query(value="DELETE FROM orders o WHERE o.member_id = :userId",nativeQuery = true)
    void deleteByUserId(@Param("userId") int userId);
}
