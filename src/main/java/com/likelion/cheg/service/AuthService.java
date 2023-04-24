package com.likelion.cheg.service;


import com.likelion.cheg.domain.enumType.Role;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ex.CustomException;
import com.likelion.cheg.handler.ex.CustomValidationApiException;
import com.likelion.cheg.handler.ex.CustomValidationException;
import com.likelion.cheg.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Controller
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //회원가입 시 역할과 암호화된 비밀번호를 insert
    @Transactional
    public User signup(SignupDto signupDto){
        String rawPassword = signupDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        Role role;

        if(signupDto.getUsername().equals("admin")){
            role = Role.ROLE_ADMIN;
        }else{
            role = Role.ROLE_USER;
        }

        User user = User.builder()
                .username(signupDto.getUsername())
                .password(encPassword)
                .name(signupDto.getName())
                .phone(signupDto.getPhone())
                .email(signupDto.getEmail())
                .role(role)
                .build();
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void uniqueUsernameCheck(String username){
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if(optionalUser.isPresent()){
            throw new CustomException("이미 존재하는 아이디입니다.");
        }
    }

}
