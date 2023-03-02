package com.likelion.cheg.domain.user;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.order.Order;
import lombok.*;
import java.util.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString(exclude = "cart")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //번호증가전략이 db를 따라간다.
	private int id;

	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"user"})
	private List<Cart> carts = new ArrayList<>();

	@OneToMany
	private List<Order> orders = new ArrayList<>();

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


	//비회원 생성 메서드
	public static User createAnonymous(){
		//8자리 주문번호 생성
		Random random = new Random();
		String number = Integer.toString(random.nextInt(8)+1);
		for(int i=0;i<7;i++){
			number += Integer.toString(random.nextInt(9));
		}
		User user = new User();
		user.setUsername("비회원_" + number);
		user.setPassword("비회원_비밀번호");
		user.setName("비회원");
		user.setRole("ROLE_GUEST");
		return user;
	}

}
