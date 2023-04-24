package com.likelion.cheg;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.enumType.Role;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class CommonMethod {

    @Autowired
    EntityManager em;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public User createUser(String username) {
        User user = User.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode("123"))
                .name("안뇽")
                .phone("01050222941")
                .email("kang@email.com")
                .address("서울")
                .role(Role.ROLE_USER)
                .build();

        em.persist(user);
        return user;
    }
    public Category createCategory(String categoryName) {
        Category category = Category.builder()
                .name(categoryName)
                .build();

        em.persist(category);
        return category;
    }
    public Product createProduct(Category category, String name, int price) {
        Product product = Product.builder()
                .category(category)
                .name(name)
                .price(price)
                .build();

        em.persist(product);
        return product;
    }
    public Cart createCart(User user, Product product, int productCount){
        Cart cart = Cart.builder()
                    .user(user)
                    .product(product)
                    .productCount(productCount)
                    .build();
        user.getCarts().add(cart);
        em.persist(cart);
        return cart;
    }
}
