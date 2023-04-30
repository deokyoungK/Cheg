package com.likelion.cheg.web.dto.auth;

import com.likelion.cheg.annotation.phone.Phone;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
public class SignupDto {
    @NotBlank
    @Length(max = 20, message = "아이디는 20자 이하로 입력해 주세요.")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$",message = "숫자와 영문이 혼합되어야 합니다.")
    private String password;

    @NotBlank
    @Length(max = 10, message = "이름은 10자 이하로 입력해주세요.")
    private String name;

    @Phone
    private String phone;

    @NotBlank
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String email;
}
