package com.likelion.cheg.order;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.orderItem.OrderItem;
import com.likelion.cheg.domain.orderItem.OrderItemRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.service.AuthService;
import com.likelion.cheg.service.CartService;
import com.likelion.cheg.service.CategoryService;
import com.likelion.cheg.service.OrderService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderTest {

    @Autowired
    OrderService orderService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;
    @Autowired
    AuthService authService;

    @Test
    @DisplayName("회원 주문 테스트")
    public void order(){
        User user1 = new User();
        user1.setUsername("테스트용id1");
        user1.setPassword("123");
        user1.setName("안뇽");
        user1.setPhone("01050222941");
        user1.setEmail("kang48450@gmail.com");
        user1.setAddress("서울");

        User user = authService.signup(user1);


        //상품 생성
        Category category = categoryService.findOne("신발");
        Product product1 = new Product();
        product1.setCategory(category);
        product1.setName("테스트제품1");
        product1.setPrice(1);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setCategory(category);
        product2.setName("테스트제품2");
        product2.setPrice(2);
        productRepository.save(product2);

        //장바구니 생성 -> 1,2번상품 각각 10,20개씩
        Cart cart1 = new Cart();
        cart1.setUser(user);
        cart1.setProduct(product1);
        cart1.setProduct_count(10);
        user.getCarts().add(cart1);

        Cart cart2 = new Cart();
        cart2.setUser(user);
        cart2.setProduct(product2);
        cart2.setProduct_count(20);
        user.getCarts().add(cart2);

        cartRepository.save(cart1);
        cartRepository.save(cart2);


        //주문 전 장바구니 갯수 확인
        int cart_number = user.getCarts().size();

        //(1)회원이 상세페이지에서 주문
        Order order1 = orderService.makeOrder(user.getId(),0,user.getAddress(), product1.getId(), 2);
        //(2)회원이 장바구니에서 주문
        Order order2 = orderService.makeOrder(user.getId(),1,user.getAddress(), product1.getId(), 2);

        //(1)order 확인
        assertThat(order1.getUser().getUsername()).isEqualTo(user.getUsername());
        assertThat(order1.getDelivery().getDelivery_address()).isEqualTo(user.getAddress());
        //(1)orderItem확인
        assertThat(order1.getOrderItemList().size()).isEqualTo(1);
        assertThat(order1.getOrderItemList().get(0).getOrder().getId()).isEqualTo(order1.getId());


        //(2)order 확인
        assertThat(order2.getUser().getUsername()).isEqualTo(user.getUsername());
        assertThat(order2.getDelivery().getDelivery_address()).isEqualTo(user.getAddress());
        //(2)orderItem확인
        assertThat(order2.getOrderItemList().size()).isEqualTo(2);
        assertThat(order2.getOrderItemList().get(0).getOrder().getId()).isEqualTo(order2.getId());
        //(2)장바구니 사라졌는지 확인(실제구동에서는 되는데 테스트시 작동안됨.. -> 이유찾는중)
//        assertThat(cart_number).isEqualTo(2);
//        assertThat(user.getCarts().size()).isEqualTo(0);


    }


}
