package com.likelion.cheg.web.dto.auth;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UsernameDto {
    @NotBlank
    @Length(max = 20, message = "아이디는 20자 이하로 입력해 주세요.")
    private String username;
}
