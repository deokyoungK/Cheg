package com.likelion.cheg.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class UserResponseDto {
    private int id;
    private String username;
    private String name;
    private String email;
    private String address;
    private String phone;
    private LocalDateTime createDate;


}
