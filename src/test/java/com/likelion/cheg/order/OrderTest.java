package com.likelion.cheg.order;

import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.service.CategoryService;
import com.likelion.cheg.service.OrderService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

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


    @Test
    public void order(){
        User user = new User();
        user.setUsername("테스트용id3");
        user.setPassword("123");
        user.setName("강덕순");
        user.setPhone("01050222941");
        user.setEmail("kang48450@gmail.com");
        user.setAddress("서울");
        userRepository.save(user);

        Category category = categoryService.findOne("패딩");

        Product product = new Product();
        product.setCategory(category);
        product.setName("테스트제품");
        product.setPrice(10000);
        productRepository.save(product);

        Order order = orderService.makeOrder(user.getId(),0,user.getAddress(), product.getId(), 2);
        assertThat(order.getUser().getUsername()).isEqualTo(user.getUsername());
        assertThat(order.getDelivery().getDelivery_status()).isEqualTo("배송전");
    }


}
