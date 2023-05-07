package com.likelion.cheg.web.dto.user;

import com.likelion.cheg.annotation.phone.Phone;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    private String address;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    @Phone
    private String phone;
}
