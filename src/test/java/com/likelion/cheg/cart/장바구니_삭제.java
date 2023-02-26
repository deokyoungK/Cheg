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
public class 장바구니_삭제 {

    @Autowired
    EntityManager em;
    @Autowired
    CartService cartService;
    @Autowired
    CommonMethod commonMethod;

    @Test
    public void 장바구니_삭제(){
        //user생성 후 회원가입
        User user = commonMethod.createUser("장바구니테스트_아이뒤");
        //카테고리 생성
        Category category = commonMethod.createCategory("장바구니테스트_카테고리");
        //product생성
        Product product1 = commonMethod.createProduct(category,"장바구니테스트_상품1",35000);
        Product product2 = commonMethod.createProduct(category,"장바구니테스트_상품2",20000);

        Cart cart1 = cartService.addCart(user, product1.getId(), 2);
        Cart cart2= cartService.addCart(user, product2.getId(), 3);

        cartService.deleteCart(user,cart2.getId());

        assertEquals("장바구니 한개만 남는지",user.getCarts().size(),1);
        assertEquals("남은 한개가 cart1인지",user.getCarts().get(0),cart1);
    }


}
