package com.likelion.cheg;

import com.likelion.cheg.domain.category.CategoryRepository;
import org.hibernate.sql.Insert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ChegApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChegApplication.class, args);
	}

}
