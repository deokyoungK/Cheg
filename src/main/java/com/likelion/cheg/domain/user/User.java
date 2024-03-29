package com.likelion.cheg.domain.user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.likelion.cheg.domain.base.BaseEntity;
import com.likelion.cheg.domain.base.BaseTimeEntity;
import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.delivery.Delivery;
import com.likelion.cheg.domain.enumType.Role;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.point.Point;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;
import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;


@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditingEntityListener.class)
@Getter
@Table(name = "USER")
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Builder.Default
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = LAZY,cascade = CascadeType.ALL)
	private List<Cart> carts = new ArrayList<>(); //장바구니

	@Builder.Default
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = LAZY)
	private List<Order> orders = new ArrayList<>(); //주문

	@OneToOne(mappedBy = "user")
	@JoinColumn(name = "point_id")
	private Point point; //포인트

	@Column(length = 50, unique = true)
	private String username; //아이디

	@Column(nullable=false)
	private String password; //비밀번호

	@Column(length = 10, nullable=false)
	private String name; //이름

	private String phone; //전화번호

	@Column(nullable=false)
	private String email; //이메일
	private String address; //주소

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role; //역할(관리자: ROLE_ADMIN, 회원: ROLE_USER, 비회원: ROLE_GUEST)


	//연관관계 편의 메서드
	private void setPoint(Point point){
		this.point = point;
		point.setUser(this);
	}

	//User 생성 메서드
	public static User createUser(String username, String password, String name, String phone, String email, Role role, Point point){
		User user = User.builder()
				.username(username)
				.password(password)
				.name(name)
				.phone(phone)
				.email(email)
				.role(role)
				.build();
		user.setPoint(point);
		return user;
	}

	//User 생성 메서드2
	public static User createUser(String username, String password, String name, String email, Role role, Point point){
		User user = User.builder()
				.username(username)
				.password(password)
				.name(name)
				.email(email)
				.role(role)
				.build();
		user.setPoint(point);
		return user;
	}

	//User 수정 메서드
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
				.role(Role.ROLE_USER)
				.build();
		return user;
	}

}
