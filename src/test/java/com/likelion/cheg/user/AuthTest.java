package com.likelion.cheg.user;


import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.service.AuthService;
import com.likelion.cheg.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
public class AuthTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;

    @Test
    @DisplayName("회원가입")
    public void signup(){
        User user = new User();
        user.setUsername("오리sㄴ");
        user.setPassword("123");
        user.setName("강덕영");
        user.setRole("ROLE_USER");

        User user1 = authService.signup(user);
        User findUser = userRepository.findByUsername(user1.getUsername());

        assertThat(user1.getId()).isEqualTo(findUser.getId());
    }

    @Test
    @DisplayName("중복아이디 예외처리")
    public void duplicateCheck(){
        User user1 = new User();
        user1.setUsername("중복닉네임");
        user1.setPassword("123");
        user1.setName("강덕영");

        User user2 = new User();
        user2.setUsername("중복닉네임");
        user2.setPassword("456");
        user2.setName("윤예지");

        authService.signup(user1);
        RuntimeException e = assertThrows(DataIntegrityViolationException.class, () -> authService.signup(user2));
        System.out.println("==================");
        System.out.println(e.getMessage());
        System.out.println("==================");
    }


}
