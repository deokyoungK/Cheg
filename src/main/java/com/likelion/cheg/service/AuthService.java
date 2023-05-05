package com.likelion.cheg.service;


import com.likelion.cheg.domain.enumType.Role;
import com.likelion.cheg.domain.point.Point;
import com.likelion.cheg.domain.point.PointRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.handler.ex.CustomBusinessException;
import com.likelion.cheg.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Controller
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PointRepository pointRepository;


    @Transactional
    public User signup(SignupDto signupDto){

        //아이디 중복 체크 다시한번
        checkDuplicateUsername(signupDto.getUsername());

        //비밀번호 암호화
        String rawPassword = signupDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        //단순히 ADMIN이라는 username으로 회원가입했을 때 ADMIN부여
        Role role;
        if(signupDto.getUsername().equals("admin")){
            role = Role.ROLE_ADMIN;
        }else{
            role = Role.ROLE_USER;
        }

        //포인트 생성
        Point point = Point.createPoint(5000);

        //User 생성
        User newUser = User.createUser(
                signupDto.getUsername(),
                encPassword,
                signupDto.getName(),
                signupDto.getPhone(),
                signupDto.getEmail(),
                role,
                point);

        try{
            pointRepository.save(point);
            userRepository.save(newUser);
        }catch(Exception e){
            throw new CustomBusinessApiException(ErrorCode.FAIL_SIGNUP);
        }

        return newUser;
    }

    @Transactional
    public void checkDuplicateUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            throw new DataIntegrityViolationException("해당 아이디는 이미 사용중입니다.");
        }
    }

}
