package com.likelion.cheg.domain.category;
import com.likelion.cheg.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Modifying
    @Query(value="INSERT INTO CATEGORY(name,createDate) VALUES(:name, NOW())",nativeQuery = true)
    int save(String name);

    @Query(value="SELECT * FROM CATEGORY c WHERE c.name=:name ",nativeQuery = true)
    Category findByCategoryName(String name);
}
