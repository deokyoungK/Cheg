package com.likelion.cheg.user;


import com.likelion.cheg.CommonMethod;
import com.likelion.cheg.domain.enumType.Role;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.service.AuthService;
import com.likelion.cheg.service.UserService;
import com.likelion.cheg.web.dto.auth.SignupDto;
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
public class 회원가입 {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;

    CommonMethod commonMethod;
    @Test
    public void 회원가입(){
        SignupDto signupDto = new SignupDto();
        signupDto.setUsername("오리");
        signupDto.setPassword("123");
        signupDto.setName("강덕영");
        signupDto.setEmail("kang48450@gmail.com");
        signupDto.setPhone("01050222961");
        User user = authService.signup(signupDto);

        User findUser = userRepository.findByUsername(user.getUsername());

        assertThat(user.getId()).isEqualTo(findUser.getId());
    }

    @Test
    public void 중복아이디_예외처리(){
        SignupDto signupDto = new SignupDto();
        signupDto.setUsername("중복닉네임");
        signupDto.setPassword("123");
        signupDto.setName("강덕영");
        signupDto.setEmail("kang48450@gmail.com");
        signupDto.setPhone("01050222961");

        SignupDto signupDto2 = new SignupDto();
        signupDto2.setUsername("중복닉네임");
        signupDto2.setPassword("111");
        signupDto2.setName("눈네지");
        signupDto2.setEmail("yoon@gmail.com");
        signupDto2.setPhone("01050222961");

        authService.signup(signupDto);
        RuntimeException e = assertThrows(DataIntegrityViolationException.class, () -> authService.signup(signupDto2));
        System.out.println("==================");
        System.out.println(e.getMessage());
        System.out.println("==================");
    }


}
