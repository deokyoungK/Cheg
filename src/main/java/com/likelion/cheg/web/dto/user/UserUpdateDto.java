package com.likelion.cheg.web.dto.user;

import com.likelion.cheg.annotation.phone.Phone;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {

    @Email
    private String email;
    private String address;
    @NotBlank
    private String name;
    @Phone
    private String phone;





}
