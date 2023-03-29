package com.likelion.cheg.domain.user;

import com.likelion.cheg.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JpaRepository상속했으면 자동으로 Ioc됨.
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
	List<User> findAll();

	@Query(value = "SELECT * FROM user u WHERE u.username LIKE %:keyword%",nativeQuery = true)
	List<User> searchByKeyword(String keyword);

}
