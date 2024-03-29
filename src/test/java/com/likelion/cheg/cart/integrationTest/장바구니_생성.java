package com.likelion.cheg.cart.integrationTest;

import com.likelion.cheg.CommonMethod;
import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.stock.Stock;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.service.CartService;
import com.likelion.cheg.web.dto.cart.AddCartDto;
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
        //재고 생성
        Stock stock = commonMethod.createStock(10);
        //product생성
        Product product = commonMethod.createProduct(category,"장바구니테스트_상품",35000,stock);

        //addCartDto생성
        AddCartDto addCartDto = new AddCartDto(product.getId(),3);

        Cart cart = cartService.addCart(user.getId(),addCartDto);

        assertEquals("장바구니-사용자 매핑이 잘 이루어졌는지",cart.getUser(),user);
        assertEquals("장바구니-상품 매핑이 잘 이루어졌는지.",cart.getProduct(),product);
        assertEquals("장바구니에 상품 수량이 맞게 들어갔는지",cart.getProductCount(),3);
    }


}
