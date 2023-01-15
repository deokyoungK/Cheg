package com.likelion.cheg.dto.mypage;


import com.likelion.cheg.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MypageResponseDto {

    private List<Order> orderList;
}
