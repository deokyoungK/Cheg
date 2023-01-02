package com.likelion.cheg.domain.user;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //번호증가전략이 db를 따라간다.
	private int id;

	@Column(length = 100, unique = true)
	private String username;

	@Column(nullable=false)
	private String password;

	@Column(nullable=false)
	private String name;

	@Column
	private String phone;

	private String email;
	private String address;
	private String role;
	private LocalDateTime createDate;
	
	@PrePersist //db에 insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	

}
