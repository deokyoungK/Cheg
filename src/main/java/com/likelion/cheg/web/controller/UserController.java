package com.likelion.cheg.web.controller;

import com.likelion.cheg.config.auth.PrincipalDetail;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.order.OrderRepository;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.service.OrderService;
import com.likelion.cheg.web.dto.order.OrderMyPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @GetMapping("/mypage/{userId}")
    public String myPage(@PathVariable int userId, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail){
        List<Order> orderList = orderRepository.loadOrderByUserId(userId);
        List<OrderMyPageResponseDto> orderListDto = orderService.makeMyPageResponseDto(orderList);
        model.addAttribute("orderListDto",orderListDto);
        return "user/mypage";

    }
}
