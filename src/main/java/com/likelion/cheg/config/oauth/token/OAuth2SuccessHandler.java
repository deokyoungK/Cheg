package com.likelion.cheg.config.oauth.token;

import com.likelion.cheg.domain.enumType.Role;
import com.likelion.cheg.domain.point.Point;
import com.likelion.cheg.domain.point.PointRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

//여기서 로그인이 완료된 사용자에게 jwt토큰을 발급한다.
@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final TokenService tokenService;
    private final UserRequestMapper userRequestMapper;
    private final UserRepository userRepository;
    private final PointRepository pointRepository;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("========OAuth2SuccessHandler 실행========");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();


        UserDto userDto = userRequestMapper.toDto(oAuth2User);

        //회원가입
        String username = userDto.getEmail();
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
        String name = userDto.getName();
        String email = userDto.getEmail();

        User findUser = userRepository.findByUsername(username);

        if(findUser == null) { //최초 로그인 시에 User 생성
            Point point = Point.createPoint(5000);

            User user = User.createUser(
                    username,
                    password,
                    name,
                    email,
                    Role.ROLE_USER,
                    point);

            pointRepository.save(point);
            User newUser = userRepository.save(user);

        }

        Token token = tokenService.generateToken(username, "USER");

        //응답 헤더에 Auth, Refresh 추가
        writeTokenResponse(response, token);

        System.out.println("==========OAuth2SuccessHandler 실행 끝===========");

        //홈으로 Redirect -> 이 부분은 원래 프론트 서버로 redirect해야될듯?
        response.sendRedirect("/");

    }

    private void writeTokenResponse(HttpServletResponse response, Token token) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        response.addHeader("Auth", token.getToken());
        response.addHeader("Refresh", token.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");

//        var writer = response.getWriter();
//        writer.println(objectMapper.writeValueAsString(token));
//        writer.flush();

    }
}
