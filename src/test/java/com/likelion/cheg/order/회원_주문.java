package com.likelion.cheg.order;

import com.likelion.cheg.CommonMethod;
import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.orderItem.OrderItemRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.service.AuthService;
import com.likelion.cheg.service.CartService;
import com.likelion.cheg.service.CategoryService;
import com.likelion.cheg.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class 회원_주문 {

    @PersistenceContext
    EntityManager em;
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
    @Autowired
    CommonMethod commonMethod;

    @Test
    public void 회원_상세페이지_주문_테스트(){
        //user생성 후 회원가입
        User user = commonMethod.createUser("테스트용_아디1123asd22");
        //카테고리 생성
        Category category = commonMethod.createCategory("테스트용_카테고리");
        //상품 생성
        Product product = commonMethod.createProduct(category,"테스트용_상품",100);

        //회원이 상세페이지에서 주문
        Order order = orderService.makeOrder(user.getId(),0,user.getAddress(), product.getId(), 2);

        //order 확인
        assertEquals("주문 시 상태는 1이 되어야함.",order.getOrderStatus(),1);
        assertEquals("주문 시 배송정보_유효성 확인.",order.getDelivery().getDeliveryAddress(),user.getAddress());
        //orderItem확인
        assertEquals("주문상품 갯수 = 장바구니 갯수",order.getOrderItemList().size(),1);
        assertEquals("주문상품이 주문을 잘 참조하고 있는지",order.getOrderItemList().get(0).getOrder().getId(),order.getId());
        //delivery확인
        assertEquals("배송상태는 '배송전'",order.getDelivery().getDeliveryStatus(),"배송전");
        assertEquals("배송이 주문을 잘 참조하고 있는지",order.getDelivery().getOrder().getId(),order.getId());
    }

    @Test
    public void 회원_장바구니_주문_테스트(){
        //user생성 후 회원가입
        User user = commonMethod.createUser("테스트용_아디22asd2222");
        //카테고리 생성
        Category category = commonMethod.createCategory("테스트용_카테고리");
        //상품 생성
        Product product = commonMethod.createProduct(category,"테스트용_상품",10);

        //장바구니 생성
        Cart cart = commonMethod.createCart(user,product,2);
        //주문 전 장바구니 갯수 확인
        int cart_number = user.getCarts().size();

        //회원이 장바구니에서 주문
        Order order = orderService.makeOrder(user.getId(),1,user.getAddress(), product.getId(), 2);

        //order 확인
        assertEquals("주문 시 상태는 1이 되어야함.",order.getOrderStatus(),1);
        assertEquals("주문 시 배송정보_유효성 확인.",order.getDelivery().getDeliveryAddress(),user.getAddress());
        //orderItem확인
        assertEquals("주문상품 갯수 = 장바구니 갯수",order.getOrderItemList().size(),1);
        assertEquals("주문상품이 주문을 잘 참조하고 있는지",order.getOrderItemList().get(0).getOrder().getId(),order.getId());
        //delivery확인
        assertEquals("배송상태는 '배송전'",order.getDelivery().getDeliveryStatus(),"배송전");
        assertEquals("배송이 주문을 잘 참조하고 있는지",order.getDelivery().getOrder().getId(),order.getId());
        //장바구니 사라졌는지 확인
        assertEquals("처음 장바구니 수량은 1",cart_number,1);
        assertEquals("주문 후 장바구니 수량은 0이 되는지",user.getCarts().size(),0);

    }
}
