package com.likelion.cheg.cart.integrationTest;

import com.likelion.cheg.CommonMethod;
import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.stock.Stock;
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
public class 장바구니_수량변경 {

    @Autowired
    EntityManager em;
    @Autowired
    CartService cartService;
    @Autowired
    CommonMethod commonMethod;

    @Test
    public void 장바구니_수량증가(){
        //user생성 후 회원가입
        User user = commonMethod.createUser("장바구니테스트_아이디222");
        //카테고리 생성
        Category category = commonMethod.createCategory("장바구니테스트_카테고리");
        //재고 생성
        Stock stock = commonMethod.createStock(10);
        //상품 생성
        Product product = commonMethod.createProduct(category,"장바구니테스트_상품",35000,stock);
        //장바구니 생성
        Cart cart = commonMethod.createCart(user,product,5);

        cartService.upCart(cart.getId());

        assertEquals("장바구니 수량이 1증가했는지",cart.getProductCount(),6);
    }
    @Test
    public void 장바구니_수량감소(){
        //user생성 후 회원가입
        User user = commonMethod.createUser("장바구니테스트_아이디333");
        //카테고리 생성
        Category category = commonMethod.createCategory("장바구니테스트_카테고리");
        //재고 생성
        Stock stock = commonMethod.createStock(10);
        //상품 생성
        Product product = commonMethod.createProduct(category,"장바구니테스트_상품",35000,stock);
        //장바구니 생성
        Cart cart = commonMethod.createCart(user,product,3);

        cartService.downCart(cart.getId());

        assertEquals("장바구니 수량이 1감소했는지",cart.getProductCount(),2);
    }

}
