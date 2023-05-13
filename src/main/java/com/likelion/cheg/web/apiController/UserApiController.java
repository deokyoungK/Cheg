package com.likelion.cheg.web.apiController;

import com.likelion.cheg.config.auth.PrincipalDetail;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.service.UserService;
import com.likelion.cheg.web.dto.CMResponse;
import com.likelion.cheg.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @DeleteMapping("api/users/{userId}")
    public ResponseEntity<CMResponse> deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new CMResponse<>(1,"회원 탈퇴 성공",""),HttpStatus.OK);
    }

    @PutMapping("api/users/{userId}")
    public ResponseEntity<CMResponse> updateUser(
            @PathVariable int userId,
            @Validated UserUpdateDto userUpdateDto,
            @AuthenticationPrincipal PrincipalDetail principalDetail) {

        User user = userService.update(userId,userUpdateDto);
        principalDetail.setUser(user); //세션정보 변경
        return new ResponseEntity<>(new CMResponse<>(1, "회원정보 변경 성공", ""), HttpStatus.OK);
    }
}
