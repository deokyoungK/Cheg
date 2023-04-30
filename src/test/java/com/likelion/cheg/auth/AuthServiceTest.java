package com.likelion.cheg.auth;

import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.service.AuthService;
import com.likelion.cheg.web.dto.auth.SignupDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class AuthServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;

    @Test
    public void 회원가입테스트(){
        String username = "오리";
        String password = "123";
        String name = "강덕영";
        String email = "kang48450@gmail.com";
        String phone = "01050222952";

        SignupDto signupDto = SignupDto.builder()
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .phone(phone)
                .build();

        User user = authService.signup(signupDto);

        User findUser = userRepository.findByUsername(user.getUsername());

        assertThat(user.getId()).isEqualTo(findUser.getId());
    }

    @Test
    public void 아이디중복테스트(){

        String username = "중복아이디테스트";
        String password = "12345";
        String name = "김철수";
        String email = "kim@gmail.com";
        String phone = "01050222951";

        SignupDto signupDto = SignupDto.builder()
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .phone(phone)
                .build();

        String username2 = "중복아이디테스트";
        String password2 = "1234512";
        String name2 = "강덕영";
        String email2 = "kang@gmail.com";
        String phone2 = "01055222951";

        SignupDto signupDto2 = SignupDto.builder()
                .username(username2)
                .password(password2)
                .name(name2)
                .email(email2)
                .phone(phone2)
                .build();

        authService.signup(signupDto);
        RuntimeException e = assertThrows(DataIntegrityViolationException.class, () -> authService.signup(signupDto2));
    }

}
