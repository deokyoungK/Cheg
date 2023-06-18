package com.likelion.cheg.config.oauth.session;

import com.likelion.cheg.config.auth.PrincipalDetail;
import com.likelion.cheg.config.oauth.session.*;
import com.likelion.cheg.domain.enumType.Role;
import com.likelion.cheg.domain.point.Point;
import com.likelion.cheg.domain.point.PointRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;
	private final PointRepository pointRepository;

	@Override
	@Transactional
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2User oauth2User = super.loadUser(userRequest);

		OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
			log.info("구글 로그인 요청");
			oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());

		}else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
			log.info("페이스북 로그인 요청");
			oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")){
			log.info("네이버 로그인 요청");
			oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")){
			log.info("카카오 로그인 요청");
			oAuth2UserInfo = new KakaoUserInfo(oauth2User.getAttributes());
		}

		String username = oAuth2UserInfo.getProvider() + oAuth2UserInfo.getUsername();
		String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
		String email = oAuth2UserInfo.getEmail();
		String name = oAuth2UserInfo.getName();

		System.out.println("=====================");
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
		System.out.println(name);
		System.out.println("=====================");

		User findUser = userRepository.findByUsername(username);

		if(findUser == null) { //최초 로그인 시
			//포인트 생성
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
;
			return new PrincipalDetail(newUser);
		}else {
			return new PrincipalDetail(findUser);
		}
	}
}
