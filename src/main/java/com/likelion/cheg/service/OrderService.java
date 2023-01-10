package com.likelion.cheg.service;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.delivery.Delivery;
import com.likelion.cheg.domain.delivery.DeliveryRepository;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.order.OrderRepository;
import com.likelion.cheg.domain.orderItem.OrderItem;
import com.likelion.cheg.domain.orderItem.OrderItemRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ex.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final DeliveryRepository deliveryRepository;
    private final ProductRepository productRepository;

    //※주문로직 리팩토링 필요※
    @Transactional
    public void makeOrder(int userId, int flag, String address, int productId, int amount){

        //비회원
        if(userId == 0){
            //delivery객체 생성
            Delivery delivery = new Delivery();
            delivery.setDelivery_address(address);
            delivery.setDelivery_status("배송전");
            deliveryRepository.save(delivery);

            //order객체 생성
            UUID number = UUID.randomUUID();

            User user = new User();
            user.setName("비회원");
            user.setPassword("비회원_password");
            user.setUsername("비회원_"+number);
            user.setRole("ROLE_GUEST");
            userRepository.save(user);

            Order order = new Order();
            order.setUser(user);
            order.setDelivery(delivery);
            order.setOrder_status(1);
            order.setOrder_number(number.toString());
            orderRepository.save(order);


            //delivery객체 기준 order에 매핑
            order.getDelivery().setOrder(order);

            //orderitem객체 생성, order에 매핑
            Product product = productRepository.findById(productId).orElseThrow(()->{
                return new CustomException("상품을 찾을 수 없습니다.");
            });
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(amount);
            orderItemRepository.save(orderItem);

        }else{ //회원

            //delivery객체 생성
            Delivery delivery = new Delivery();
            delivery.setDelivery_address(address);
            delivery.setDelivery_status("배송전");
            deliveryRepository.save(delivery);

            //order객체 생성
            UUID number = UUID.randomUUID();

            User user = userRepository.findById(userId).orElseThrow(()->{
                return new CustomException("유저를 찾을 수 없습니다.");
            });

            Order order = new Order();
            order.setUser(user);
            order.setDelivery(delivery);
            order.setOrder_status(1);
            order.setOrder_number(number.toString());

            orderRepository.save(order);

            //delivery객체 기준 order에 매핑
            order.getDelivery().setOrder(order);


            //상세에서 결제한 회원
            if(flag == 0){
                //orderitem객체 생성, order에 매핑
                Product product = productRepository.findById(productId).orElseThrow(()->{
                    return new CustomException("상품을 찾을 수 없습니다.");
                });
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setQuantity(amount);
                orderItemRepository.save(orderItem);


            }else{ //장바구니에서 결제한 회원

                //orderitem객체 생성, order에 매핑
                List<Cart> cartList = cartRepository.loadCartByUserId(userId);
                for(Cart cart : cartList){
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(cart.getProduct());
                    orderItem.setQuantity(cart.getProduct_count());
                    orderItemRepository.save(orderItem);

                    //주문되면 장바구니에서는 삭제
                    cartRepository.deleteById(cart.getId());
                }
            }


        }
    }

}
