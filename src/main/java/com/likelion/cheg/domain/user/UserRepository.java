package com.likelion.cheg.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository상속했으면 자동으로 Ioc됨.
public interface UserRepository extends JpaRepository<User, Integer>{

	// JPA query method
	User findByUsername(String username);
	List<User> findAll();



}
