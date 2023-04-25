package com.likelion.cheg.domain.user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.enumType.Role;
import com.likelion.cheg.domain.order.Order;
import lombok.*;
import java.util.*;
import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@ToString(exclude = "cart")
@Getter
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Builder.Default
	@JsonIgnore
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	private List<Cart> carts = new ArrayList<>(); //장바구니

	@Builder.Default
	@JsonIgnore
	@OneToMany
	private List<Order> orders = new ArrayList<>(); //주문

	@Column(length = 100, unique = true)
	private String username; //아이디

	@Column(nullable=false)
	private String password; //비밀번호

	@Column(nullable=false)
	private String name; //이름

	@Column
	private String phone; //전화번호

	private String email; //이메일
	private String address; //주소

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role; //역할(관리자: ROLE_ADMIN, 회원: ROLE_USER, 비회원: ROLE_GUEST)

	private LocalDateTime createDate; //날짜
	
	@PrePersist //db에 insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	//회원 수정 메서드
	public void changeUser(String name, String address, String email, String phone){
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}


	//비회원 생성 메서드
	public static User createAnonymous(){
		//8자리 주문번호 생성
		Random random = new Random();
		String number = Integer.toString(random.nextInt(8)+1);
		for(int i=0;i<7;i++){
			number += Integer.toString(random.nextInt(9));
		}
		User user = User.builder()
				.username("비회원_" + number)
				.password("비회원_비밀번호")
				.name("비회원")
				.role(Role.ROLE_GUEST)
				.build();
		return user;
	}

}
