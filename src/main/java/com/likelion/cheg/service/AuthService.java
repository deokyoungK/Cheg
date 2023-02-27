package com.likelion.cheg.service;


import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ex.CustomException;
import com.likelion.cheg.handler.ex.CustomValidationApiException;
import com.likelion.cheg.handler.ex.CustomValidationException;
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

    @Transactional
    public User signup(User user){
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        user.setPassword(encPassword); //암호화된 패스워드 저장

        if(user.getUsername().equals("admin")){
            user.setRole("ROLE_ADMIN");
        }else{
            user.setRole("ROLE_USER");
        }

        User userEntity = userRepository.save(user);
        return userEntity;
    }

    @Transactional
    public void uniqueUsernameCheck(String username){
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if(optionalUser.isPresent()){
            throw new CustomException("이미 존재하는 아이디입니다.");
        }
    }

}
