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
        //재고 생성
        Stock stock = commonMethod.createStock(10);
        //product생성
        Product product1 = commonMethod.createProduct(category,"장바구니테스트_상품1",35000,stock);
        Product product2 = commonMethod.createProduct(category,"장바구니테스트_상품2",20000,stock);

        //addCartDto생성
        AddCartDto addCartDto = new AddCartDto(product1.getId(),2);
        AddCartDto addCartDto2 = new AddCartDto(product2.getId(),3);

        Cart cart1 = cartService.addCart(user.getId(), addCartDto);
        Cart cart2= cartService.addCart(user.getId(), addCartDto2);

        cartService.deleteCart(user.getId(),cart2.getId());

        assertEquals("장바구니 한개만 남는지",user.getCarts().size(),1);
        assertEquals("남은 한개가 cart1인지",user.getCarts().get(0),cart1);
    }
}
