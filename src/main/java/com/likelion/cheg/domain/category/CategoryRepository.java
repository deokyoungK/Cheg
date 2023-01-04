package com.likelion.cheg.domain.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Modifying
    @Query(value="INSERT INTO category(name,createDate) VALUES(:name, NOW())",nativeQuery = true)
    int save(String name);
}
