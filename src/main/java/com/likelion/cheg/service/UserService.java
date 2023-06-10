package com.likelion.cheg.service;

import com.likelion.cheg.domain.cart.Cart;
import com.likelion.cheg.domain.cart.CartRepository;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.order.OrderRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import com.likelion.cheg.handler.ex.CustomBusinessException;
import com.likelion.cheg.web.dto.user.UserResponseDto;
import com.likelion.cheg.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public List<UserResponseDto> makeResponseDto(List<User> userList){
        List<UserResponseDto> userListDtos = userList.stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getName(),
                        user.getEmail(),
                        user.getAddress(),
                        user.getPhone()))
                .collect(Collectors.toList());
        return userListDtos;
    }


    @Transactional
    public void deleteUser(int userId){
        User user = userRepository.findById(userId).orElseThrow(()->
                new CustomBusinessApiException(ErrorCode.NOT_FOUND_USER));

        userRepository.deleteById(userId);
    }


    @Transactional
    public User update(int userId, UserUpdateDto userUpdateDto){
        User user = userRepository.findById(userId).orElseThrow(()->{
            return new CustomBusinessApiException(ErrorCode.NOT_FOUND_USER);
        });

        user.changeUser(userUpdateDto.getName(),
                userUpdateDto.getAddress(),
                userUpdateDto.getEmail(),
                userUpdateDto.getPhone());

        return user;
    }
}
