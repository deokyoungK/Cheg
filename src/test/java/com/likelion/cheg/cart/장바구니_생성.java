package com.likelion.cheg.cart;

import com.likelion.cheg.CommonMethod;
import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class 장바구니_생성 {

    @Autowired
    EntityManager em;
    @Autowired
    CartService cartService;
    @Autowired
    CommonMethod commonMethod;

    @Test
    public void 장바구니_생성(){
        //user생성 후 회원가입
        User user = commonMethod.createUser("장바구니테스트_아이디");
        //카테고리 생성
        Category category = commonMethod.createCategory("장바구니테스트_카테고리");
        //product생성
        Product product = commonMethod.createProduct(category,"장바구니테스트_상품",35000);

        Cart cart = cartService.addCart(user,product.getId(),2);

        assertEquals("장바구니-사용자 매핑이 잘 이루어졌는지",cart.getUser(),user);
        assertEquals("장바구니-상품 매핑이 잘 이루어졌는지.",cart.getProduct(),product);
        assertEquals("장바구니에 상품 수량이 맞게 들어갔는지",cart.getProduct_count(),2);
    }


}
