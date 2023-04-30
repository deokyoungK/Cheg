package com.likelion.cheg.web.dto.auth;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class PasswordDto {

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$",message = "숫자와 영문이 혼합되어야 합니다.")
    private String password;
}
