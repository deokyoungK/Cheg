package com.likelion.cheg.user;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.enumType.Role;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.order.OrderRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.handler.ex.CustomBusinessException;
import com.likelion.cheg.service.UserService;
import com.likelion.cheg.web.dto.user.UserUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.transaction.Transactional;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void 회원정보변경_테스트_성공() {
        // given
        User oldUser = User.builder()
                .username("회원정보 변경 테스트 아이디")
                .password("123")
                .name("oldName")
                .phone("oldPhone")
                .email("oldEmail")
                .address("oldAddress")
                .role(Role.ROLE_USER)
                .build();

        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setName("newName");
        userUpdateDto.setAddress("newAddress");
        userUpdateDto.setEmail("newEmail");
        userUpdateDto.setPhone("newPhone");

        //반드시 정해진 결과를 반환하도록 mocking
        given(userRepository.findById(anyInt())).willReturn(Optional.of(oldUser));

        // when
        User newUser = userService.update(1, userUpdateDto);

        // then
        assertThat(newUser.getEmail()).isEqualTo("newEmail");
        assertThat(newUser.getAddress()).isEqualTo("newAddress");
        assertThat(newUser.getName()).isEqualTo("newName");
        assertThat(newUser.getPhone()).isEqualTo("newPhone");
    }

    @Test(expected = CustomBusinessApiException.class)
    public void 회원정보변경_테스트_실패() {
        // given
        int userId = 1;
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setName("newName");
        userUpdateDto.setAddress("newAddress");
        userUpdateDto.setEmail("newEmail");
        userUpdateDto.setPhone("newPhone");

        //User 존재 X
        given(userRepository.findById(userId)).willReturn(Optional.empty());

        // when
        User user = userService.update(1, userUpdateDto);

        // then
        fail("CustomBusinessApiException 예외 발생하지 않음."); //예외 발생하지 않으면 fail
    }

    @Test
    public void 회원삭제_테스트_성공() {
        // given
        int userId = 1;
        User user = User.builder()
                .id(userId)
                .username("회원삭제 테스트 아이디")
                .password("123")
                .build();

        //장바구니 생성, 회원과 매핑
        List<Cart> carts = new ArrayList<>();
        Cart cart = Cart.builder()
                .id(1)
                .user(user)
                .build();
        carts.add(cart);

        //반드시 정해진 결과를 반환하도록 mocking
        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        System.out.println("===========");
        System.out.println(userRepository.findById(1));
        System.out.println("===========");
        // when
        userService.deleteUser(userId);
        System.out.println("===========");
        System.out.println(userRepository.findById(1));
        System.out.println("===========");
        // then
//        assertEquals("user지워졌는지 확인", userRepository.findById(userId), Optional.empty());
    }


    @Test(expected = CustomBusinessApiException.class)
    public void 회원삭제_테스트_실패() {
        // given
        int userId = 1;
        //User 존재 X
        given(userRepository.findById(userId)).willReturn(Optional.empty());

        // when
        userService.deleteUser(userId);

        // then
        fail("CustomBusinessApiException 예외 발생하지 않음."); //예외 발생하지 않으면 fail
    }
}
