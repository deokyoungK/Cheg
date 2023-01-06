package com.likelion.cheg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMResponseDto<T> {
    private int code; //1성공 -1실패
    private String message;
    private T data;
}