package com.likelion.cheg.service;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.delivery.Delivery;
import com.likelion.cheg.domain.delivery.DeliveryRepository;
import com.likelion.cheg.domain.enumType.DeliveryStatus;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.order.OrderRepository;
import com.likelion.cheg.domain.orderItem.OrderItem;
import com.likelion.cheg.domain.orderItem.OrderItemRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ex.CustomBusinessException;
import com.likelion.cheg.web.dto.order.OrderMyPageResponseDto;
import com.likelion.cheg.web.dto.order.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final DeliveryRepository deliveryRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public List<OrderMyPageResponseDto> makeMyPageResponseDto(List<Order> orderList){
        List<OrderMyPageResponseDto> orderListDtos = orderList.stream()
                .map(order -> new OrderMyPageResponseDto(
                        order.getOrderNumber(),
                        order.getOrderProductCount(),
                        order.getOrderStatus(),
                        order.getOrderPrice(),
                        order.getOrderItemList().get(0).getProduct().getBrand(),
                        order.getOrderItemList().get(0).getProduct().getUrl(),
                        order.getOrderItemList().get(0).getProduct().getName(),
                        order.getDelivery().getDeliveryStatus()))
                .collect(Collectors.toList());
        return orderListDtos;
    }

    public List<OrderResponseDto> makeResponseDto(List<Order> orderList){
        List<OrderResponseDto> orderListDtos = orderList.stream()
                .map(order -> new OrderResponseDto(
                        order.getOrderNumber(),
                        order.getCreateDate(),
                        order.getOrderStatus(),
                        order.getUser().getUsername()))
                .collect(Collectors.toList());
        return orderListDtos;
    }


    @Transactional
    public Order makeOrder(int userId, int flag, String address, int productId, int amount){

        //delivery 생성
        Delivery delivery = Delivery.createDelivery(address, DeliveryStatus.배송전);

        if(userId == 0){ //비회원의 경우
            //비회원 생성
            User user = User.createAnonymous();

            //상품 찾기
            Product product = productRepository.findById(productId).orElseThrow(()->{
                return new CustomBusinessException("상품을 찾을 수 없습니다.");
            });
            //주문상품 생성
            List<OrderItem> orderItemList = new ArrayList<>(); //리스트의 형태로 Order에 넣어줘야하기에 선언
            OrderItem orderItem = OrderItem.createOrderItem(product,product.getPrice(),amount);

            //주문 생성
            orderItemList.add(orderItem);
            Order order = Order.createOrder(user,delivery,orderItemList);

            userRepository.save(user);
            deliveryRepository.save(delivery);
            orderItemRepository.save(orderItem);
            orderRepository.save(order);

            return order;
        }else{ //회원의 경우
            //회원 찾기
            User user = userRepository.findById(userId).orElseThrow(()->{
                return new CustomBusinessException("회원를 찾을 수 없습니다.");
            });
            if(flag == 0){  //상세페이지일때
                //상품 찾기
                Product product = productRepository.findById(productId).orElseThrow(()->{
                    return new CustomBusinessException("상품을 찾을 수 없습니다.");
                });
                //주문상품 생성
                List<OrderItem> orderItemList = new ArrayList<>(); //리스트의 형태로 Order에 넣어줘야하기에 선언
                OrderItem orderItem = OrderItem.createOrderItem(product,product.getPrice(),amount);

                //주문 생성
                orderItemList.add(orderItem);
                Order order = Order.createOrder(user,delivery, orderItemList );

                deliveryRepository.save(delivery);
                orderItemRepository.save(orderItem);
                orderRepository.save(order);

                return order;
            }else{  //장바구니일때
                //장바구니 찾기
                List<Cart> cartList = cartRepository.loadCartByUserId(userId);
                List<OrderItem> orderItemList = new ArrayList<>();
                for(Cart cart : cartList){
                    //주문상품 생성
                    OrderItem orderItem = OrderItem.createOrderItem(cart.getProduct(),cart.getProduct().getPrice(),cart.getProductCount());
                    orderItemList.add(orderItem);

                    //장바구니에서는 삭제
                    cartRepository.deleteById(cart.getId());
                    user.getCarts().remove(cart);
                }
                //주문 생성
                Order order = Order.createOrder(user,delivery,orderItemList);

                deliveryRepository.save(delivery);
                for(OrderItem orderItem : order.getOrderItemList()){
                    orderItemRepository.save(orderItem);
                }
                orderRepository.save(order);
                return order;
            }
        }
    }

//    @Transactional
//    public Order makeOrderItem(Order order, int flag, int productId, int amount, int userId){
//        //flag는 상세(0)/장바구니(1) 구분
//        //productId는 [상세]결제일 때 상품Id로 orderitem을 만들기 위해 필요
//        //amount는 [상세]결제일 때 orderitem을 만들기 위해 필요
//        //userId는 [장바구니]결제일 때 유저 장바구니상품을 orderitem으로 만들기 위해 필요
//
//        if(flag == 0){ //[상세]에서 결제
//            Product product = productRepository.findById(productId).orElseThrow(()->{
//                return new CustomException("상품을 찾을 수 없습니다.");
//            });
//
//            OrderItem orderItem = new OrderItem(order, product, amount);
//
//            //order에 매핑
//            order.getOrderItemList().add(orderItem);
//
//            orderItem.calculateTotalPrice();
//            orderItemRepository.save(orderItem);
//            return order;
//
//        }else{ //[장바구니]에서 결제
//            User user = userRepository.findById(userId).orElseThrow(()->{
//                return new CustomException("사용자를 찾을 수 없습니다.");
//            });
//            List<Cart> cartList = cartRepository.loadCartByUserId(userId);
//            for(Cart cart : cartList){
//                OrderItem orderItem = new OrderItem(order, cart.getProduct(), cart.getProduct_count());
//
//                //order에 매핑
//                order.getOrderItemList().add(orderItem);
//
//                orderItem.calculateTotalPrice();
//                orderItemRepository.save(orderItem);
//
//                //주문되면 장바구니에서는 삭제
//                cartRepository.deleteById(cart.getId());
//                user.getCarts().remove(cart);
//            }
//            return order;
//        }

//    }

}
