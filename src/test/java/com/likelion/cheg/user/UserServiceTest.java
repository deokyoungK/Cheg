package com.likelion.cheg.user;

import com.likelion.cheg.domain.enumType.Role;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.handler.ex.CustomBusinessException;
import com.likelion.cheg.service.UserService;
import com.likelion.cheg.web.dto.user.UserUpdateDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void 회원정보변경_테스트_성공() {
        // given
        User oldUser = User.builder()
                .username("회원정보변경테스트아이디")
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
        int userId = 100;
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setName("newName");
        userUpdateDto.setAddress("newAddress");
        userUpdateDto.setEmail("newEmail");
        userUpdateDto.setPhone("newPhone");

        //User 존재 X
        given(userRepository.findById(userId)).willReturn(Optional.empty());

        // when
        User user = userService.update(100, userUpdateDto);

        // then
        fail("CustomBusinessApiException 예외 발생하지 않음."); //예외 발생하지 않으면 fail
    }

}
