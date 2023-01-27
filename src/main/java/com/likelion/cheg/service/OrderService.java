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
import org.aspectj.weaver.ast.Or;
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


    @Transactional
    public List<Order> searchOrderByKeyword(String keyword){
        List<Order> orderList = orderRepository.searchByKeyword(keyword);
        return orderList;
    }

    @Transactional
    public List<Order> loadAll(){
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    @Transactional
    public void calculateCount(int userId){
        List<Order> orderList = orderRepository.loadOrderByUserId(userId);

        //Order에 상품갯수 넣기
        for(Order order : orderList){
            order.setOrder_product_count(order.getOrderItemList().size());
            orderRepository.save(order);
        }
    }
    @Transactional
    public void calculatePrice(int userId){
        List<Order> orderList = orderRepository.loadOrderByUserId(userId);

        // Order에 주문상품가격 총합 넣기
        for(Order order : orderList){
            int price = 0;
            for(int i=0;i<order.getOrderItemList().size();i++){
                price += order.getOrderItemList().get(i).getTotal_price();

            }

            order.setOrder_price(price);
            orderRepository.save(order);
        }
    }



    @Transactional
    public Order makeOrder(int userId, int flag, String address, int productId, int amount){

        //8자리 주문번호 생성
        Random random = new Random();
        String number = Integer.toString(random.nextInt(8)+1);
        for(int i=0;i<7;i++){
            number += Integer.toString(random.nextInt(9));
        }

        //delivery 생성
        Delivery delivery = new Delivery(address,"배송전");
        deliveryRepository.save(delivery);

        //비회원, 회원구분
        if(userId == 0){
            //비회원user 생성 후 order 생성
            User user = new User("비회원","비회원_password","비회원_"+number,"ROLE_GUEST");
            userRepository.save(user);
            Order order = new Order(user,delivery,1,number.toString());
            order.getDelivery().setOrder(order);
            orderRepository.save(order);

            Order myOrder = makeOrderItem(order,0,productId,amount,userId);
            return myOrder;
        }else{
            //회원찾은 후 order 생성
            User user = userRepository.findById(userId).orElseThrow(()->{
                return new CustomException("회원를 찾을 수 없습니다.");
            });
            Order order = new Order(user,delivery,1,number.toString());
            order.getDelivery().setOrder(order);
            orderRepository.save(order);

            Order myOrder = makeOrderItem(order,flag,productId,amount,userId);
            return myOrder;
        }
    }

    @Transactional
    public Order makeOrderItem(Order order, int flag, int productId, int amount, int userId){
        //flag는 상세(0)/장바구니(1) 구분
        //productId는 [상세]결제일 때 상품Id로 orderitem을 만들기 위해 필요
        //amount는 [상세]결제일 때 orderitem을 만들기 위해 필요
        //userId는 [장바구니]결제일 때 유저 장바구니상품을 orderitem으로 만들기 위해 필요

        if(flag == 0){ //[상세]에서 결제
            Product product = productRepository.findById(productId).orElseThrow(()->{
                return new CustomException("상품을 찾을 수 없습니다.");
            });

            OrderItem orderItem = new OrderItem(order, product, amount);

            //order에 매핑
            order.getOrderItemList().add(orderItem);

            orderItem.calculateTotalPrice();
            orderItemRepository.save(orderItem);
            return order;

        }else{ //[장바구니]에서 결제
            List<Cart> cartList = cartRepository.loadCartByUserId(userId);
            for(Cart cart : cartList){
                OrderItem orderItem = new OrderItem(order, cart.getProduct(), cart.getProduct_count());

                //order에 매핑
                order.getOrderItemList().add(orderItem);

                orderItem.calculateTotalPrice();
                orderItemRepository.save(orderItem);

                //주문되면 장바구니에서는 삭제
                cartRepository.deleteById(cart.getId());

            }
            return order;
        }

    }

}
