package com.likelion.cheg.service;

import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ex.CustomValidationApiException;
import com.likelion.cheg.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User update(int userId, UserUpdateDto userUpdateDto){

        User user = userRepository.findById(userId).orElseThrow(()->{
            return new CustomValidationApiException("찾을 수 없는 id입니다.");
        });

        user.setEmail(userUpdateDto.getEmail());
        user.setAddress(userUpdateDto.getAddress());
        user.setName(userUpdateDto.getName());
        user.setPhone(userUpdateDto.getPhone());
        return user;
    }
}
