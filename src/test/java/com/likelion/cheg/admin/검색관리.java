package com.likelion.cheg.admin;

import com.likelion.cheg.CommonMethod;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.order.OrderRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.stock.Stock;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.service.OrderService;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class 검색관리 {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    CommonMethod commonMethod;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void 회원검색(){
        User user = commonMethod.createUser("AbirdBC");
        User user2 = commonMethod.createUser("ABCbird");
        User user3 = commonMethod.createUser("ABCD");

        String keyword = "bird";
        List<User> userList = userRepository.searchByKeyword(keyword);

        assertEquals("검색된 User는 총 2명",userList.size(),2);
        assertEquals("검색된 User의 아이디 확인",userList.contains(user),true);
        assertEquals("검색된 User2의 아이디 확인",userList.contains(user2),true);
        assertEquals("검색된 User3의 아이디 확인",userList.contains(user3),false);
    }
    @Test
    public void 상품검색(){
        Category category = commonMethod.createCategory("NEW카테고리");
        Stock stock = commonMethod.createStock(20);
        Stock stock2 = commonMethod.createStock(20);
        Stock stock3 = commonMethod.createStock(20);
        Product product = commonMethod.createProduct(category,"jordon_Tshirts",10000,stock);
        Product product2 = commonMethod.createProduct(category,"shoes_jordon",28000,stock2);
        Product product3 = commonMethod.createProduct(category,"converse",6000,stock3);

        String keyword = "jordon";
        List<Product> productList = productRepository.findAllByKeyword(keyword);

        assertEquals("검색된 product는 총 2개",productList.size(),2);
        assertEquals("검색된 product의 이름 확인",productList.contains(product),true);
        assertEquals("검색된 product2의 이름 확인",productList.contains(product2),true);
        assertEquals("검색된 product3의 이름 확인",productList.contains(product3),false);
    }
}
