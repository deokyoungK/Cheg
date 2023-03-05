package com.likelion.cheg.admin;

import com.likelion.cheg.CommonMethod;
import com.likelion.cheg.domain.order.OrderRepository;
import com.likelion.cheg.domain.product.ProductRepository;
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

    @Test
    public void 회원검색(){
        User user = commonMethod.createUser("AbirdBC");
        User user2 = commonMethod.createUser("ABCbird");
        User user3 = commonMethod.createUser("ABCD");

        String keyword = "bird";
        List<User> totalUser = userRepository.findAll();
        List<User> userList = userService.searchUserByKeyword(keyword);

        assertEquals("총 User 수는 3명",totalUser.size(),3);
        assertEquals("검색된 User는 총 2명",userList.size(),2);
        assertEquals("검색된 User의 아이디 확인",userList.contains(user),true);
        assertEquals("검색된 User의 아이디 확인",userList.contains(user2),true);
        assertEquals("검색된 User의 아이디 확인",userList.contains(user3),false);
    }
    @Test
    public void 상품검색(){
    }
    @Test
    public void 주문검색(){
    }
}
