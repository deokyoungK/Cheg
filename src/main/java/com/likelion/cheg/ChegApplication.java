package com.likelion.cheg;

import com.likelion.cheg.domain.category.CategoryRepository;
import org.hibernate.sql.Insert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChegApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChegApplication.class, args);
	}

}
