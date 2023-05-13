package com.likelion.cheg.web.apiController;

import com.likelion.cheg.service.AuthService;
import com.likelion.cheg.web.dto.CMResponse;
import com.likelion.cheg.web.dto.auth.PasswordDto;
import com.likelion.cheg.web.dto.auth.SignupDto;
import com.likelion.cheg.web.dto.auth.UsernameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApiControlller {

    private final AuthService authService;

    @PostMapping("/api/auth/checkUsername")
    public ResponseEntity<?> checkUsername(@Validated UsernameDto usernameDto){
        authService.checkDuplicateUsername(usernameDto.getUsername());
        return new ResponseEntity<>(new CMResponse<>(1,"중복회원 검사 성공",""),HttpStatus.OK);
    }

    @PostMapping("/api/auth/checkPassword")
    public ResponseEntity<?> checkPassword(@Validated PasswordDto passwordDto){
        return new ResponseEntity<>(new CMResponse<>(1,"비밀번호 검사 성공",""),HttpStatus.OK);
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> signup(@Validated SignupDto signupDto){
        authService.signup(signupDto);
        return new ResponseEntity<>(new CMResponse<>(1,"회원가입 완료",""), HttpStatus.OK);
    }

}
