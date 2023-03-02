package com.likelion.cheg.user;

import com.likelion.cheg.CommonMethod;

import com.likelion.cheg.domain.user.User;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class 로그인 {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CommonMethod commonMethod;
    @Autowired
    WebApplicationContext context;

    //mockMvc 객체 생성, Spring Security 환경 setup
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void 로그인_성공() throws Exception{
        //회원가입
        User user = commonMethod.createUser("로그인 테스트용_ID");

        String username = "로그인 테스트용_ID";
        String password = "123";

        mockMvc.perform(formLogin("/auth/login").user(username).password(password))
                .andDo(print())

                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void 로그인_실패() throws Exception{
        String username = "존재하지 않는 아이디";
        String password = "123";

        mockMvc.perform(formLogin("/auth/login").user(username).password(password))
                .andExpect(unauthenticated());
    }
}
