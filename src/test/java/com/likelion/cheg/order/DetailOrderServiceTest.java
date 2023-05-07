package com.likelion.cheg.order;

import com.likelion.cheg.domain.delivery.DeliveryRepository;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.order.OrderRepository;
import com.likelion.cheg.domain.orderItem.OrderItemRepository;
import com.likelion.cheg.domain.point.Point;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.service.OrderService;
import com.likelion.cheg.web.dto.pay.PaymentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailOrderServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private DeliveryRepository deliveryRepository;
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;

    @Test
    public void 상세페이지주문_포인트사용_성공(){
        // given
        int userId = 1;
        int productId = 1;

        String deliveryAddress = "서초구";
        int price = 5000; //상품 가격
        int stockQuantity = 100; //상품 재고
        int amount = 3; //구매 수량

        int pointTotal = 3000; //회원 총 포인트
        int pointAmount = 1000; //포인트 사용 금액

        Point point = Point.createPoint(pointTotal);
        //회원 세팅
        User user = User.builder()
                .id(userId).point(point).build();
        //상품 세팅
        Product product = Product.builder()
                .id(productId)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
        //DTO 세팅
        PaymentDto paymentDto = PaymentDto.builder()
                .address(deliveryAddress)
                .productId(productId)
                .amount(amount)
                .pointAmount(pointAmount)
                .flag(0)
                .build();


        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(productRepository.findById(productId)).willReturn(Optional.of(product));


        //when
        Order order = orderService.makeOrder(userId,paymentDto);

        //then
        assertEquals("주문의 배송주소가 맞는지 확인",order.getDelivery().getDeliveryAddress(),deliveryAddress);
        assertEquals("주문의 상품이 알맞은지 확인",order.getOrderItemList().get(0).getProduct(),product);
        assertEquals("주문의 포인트가 알맞은지 확인",order.getPointAmount(),pointAmount);

        assertEquals("주문 후 상품의 재고가 줄어들었는지 확인 ",product.getStockQuantity(),stockQuantity - amount);
        assertEquals("주문 후 회원의 포인트가 차감됐는지 확인 ",user.getPoint().getAmount(),pointTotal - pointAmount);

    }

    @Test
    public void 상페페이지주문_포인트미사용_성공(){
        // given
        int userId = 1;
        int productId = 1;

        String deliveryAddress = "서초구";
        int price = 5000; //상품 가격
        int stockQuantity = 100; //상품 재고
        int amount = 3; //구매 수량

        int pointTotal = 3000; //회원 총 포인트
        int pointAmount = 0; //포인트 사용 금액

        Point point = Point.createPoint(pointTotal);
        //회원 세팅
        User user = User.builder()
                .id(userId).point(point).build();
        //상품 세팅
        Product product = Product.builder()
                .id(productId)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
        //DTO 세팅
        PaymentDto paymentDto = PaymentDto.builder()
                .address(deliveryAddress)
                .productId(productId)
                .amount(amount)
                .pointAmount(pointAmount)
                .flag(0)
                .build();


        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(productRepository.findById(productId)).willReturn(Optional.of(product));

        //when
        Order order = orderService.makeOrder(userId,paymentDto);

        //then
        assertEquals("주문의 배송주소가 맞는지 확인",order.getDelivery().getDeliveryAddress(),deliveryAddress);
        assertEquals("주문의 상품이 알맞은지 확인",order.getOrderItemList().get(0).getProduct(),product);
        assertEquals("주문의 포인트가 알맞은지 확인",order.getPointAmount(),pointAmount);

        assertEquals("주문 후 상품의 재고가 줄어들었는지 확인 ",product.getStockQuantity(),stockQuantity - amount);
        assertEquals("주문 후 회원의 포인트가 차감됐는지 확인 ",user.getPoint().getAmount(),pointTotal);

    }

    @Test
    public void 상세페이지주문_실패_상품없음(){
        // given
        int userId = 1;
        int productId = 1;
        int pointTotal = 3000; //회원 총 포인트
        int pointAmount = 1000; //포인트 사용 금액

        Point point = Point.createPoint(pointTotal);
        //회원 세팅
        User user = User.builder()
                .id(userId).point(point).build();

        //DTO 세팅
        PaymentDto paymentDto = PaymentDto.builder()
                .address("서울")
                .productId(productId)
                .amount(3)
                .pointAmount(pointAmount)
                .flag(0)
                .build();

        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(productRepository.findById(productId)).willReturn(Optional.empty());

        //when
        Throwable e = assertThrows(CustomBusinessApiException.class,
                () -> orderService.makeOrder(userId, paymentDto));

        // then
        assertEquals(ErrorCode.NOT_FOUND_PRODUCT, ((CustomBusinessApiException) e).getErrorCode());

    }

    @Test
    public void 상세페이지주문_실패_포인트초과(){
        // given
        int userId = 1;
        int productId = 1;
        int pointTotal = 3000; //회원 총 포인트
        int pointAmount = 4000; //포인트 사용 금액

        Point point = Point.createPoint(pointTotal);
        //회원 세팅
        User user = User.builder()
                .id(userId).point(point).build();
        //상품 세팅
        Product product = Product.builder()
                .id(productId)
                .stockQuantity(100)
                .build();
        //DTO 세팅
        PaymentDto paymentDto = PaymentDto.builder()
                .address("서울")
                .productId(productId)
                .amount(3)
                .pointAmount(pointAmount)
                .flag(0)
                .build();

        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(productRepository.findById(productId)).willReturn(Optional.of(product));

        //when
        Throwable e = assertThrows(CustomBusinessApiException.class,
                () -> orderService.makeOrder(userId, paymentDto));

        // then
        assertEquals(ErrorCode.EXCEED_POINT, ((CustomBusinessApiException) e).getErrorCode());

    }
}
