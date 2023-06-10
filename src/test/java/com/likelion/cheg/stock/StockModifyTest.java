package com.likelion.cheg.stock;

import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.enumType.Role;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.stock.Stock;
import com.likelion.cheg.domain.stock.StockRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.service.OrderService;
import com.likelion.cheg.web.dto.pay.PaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Cacheable;
import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StockModifyTest {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    EntityManager em;

    @Test
    public void 상품재고_감소(){
        //id가 1L인 재고에 100개 수량 저장
        Stock stock = Stock.builder().id(15L).quantity(100).build();
        stockRepository.save(stock);

        Stock findStock = stockRepository.findById(15L).orElseThrow();
        findStock.decrease(1);
        assertEquals(99,findStock.getQuantity());
    }






    @Test
    public void 동시에_100개_요청() throws InterruptedException {

        int threadCount = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(32);//32개 스레드 생성
        CountDownLatch latch = new CountDownLatch(threadCount); //스레드 완료 대기를 위해

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    //독립적인 user 생성
                    User user = User.builder()
                            .id(1)
                            .name("kang")
                            .password("123")
                            .email("kang489450@gmail.com")
                            .role(Role.ROLE_ADMIN)
                            .build();
                    userRepository.save(user);

                    //독립적인 paymentDto 생성
                    PaymentDto paymentDto = PaymentDto.builder()
                            .address("")
                            .productId(1)
                            .amount(1) //1개씩 감소한다.
                            .pointAmount(0)
                            .flag(1)
                            .build();

                    Optional<User> findUser = userRepository.findById(1);
                    System.out.println(findUser);

                    orderService.makeOrder(1,paymentDto); //문제의 메서드 호출
                } finally {
                    latch.countDown(); //완료되었음을 알림
                }
            });
        }
        latch.await();

        Stock findStock = stockRepository.findById(1L).orElseThrow();

        //100 -(1*100) = 0을 예상
        assertEquals(5, findStock.getQuantity());

    }
}
