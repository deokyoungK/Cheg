package com.likelion.cheg.web.dto.order;

import com.likelion.cheg.domain.enumType.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class OrderResponseDto {
    private String orderNumber;
    private LocalDateTime createDate;
    private OrderStatus orderStatus;
    private String username; //회원아이디
}
