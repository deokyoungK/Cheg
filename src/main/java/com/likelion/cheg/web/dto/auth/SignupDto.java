package com.likelion.cheg.web.dto.auth;

import com.likelion.cheg.Annotation.phone.Phone;
import com.likelion.cheg.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignupDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;

    @Phone
    private String phone;

    @NotBlank
    private String email;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .phone(phone)
                .email(email)
                .build();
    }
}
