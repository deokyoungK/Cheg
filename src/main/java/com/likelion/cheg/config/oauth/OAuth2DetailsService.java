package com.likelion.cheg.config.oauth;

import com.likelion.cheg.config.auth.PrincipalDetail;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;
	
	@Override
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


		User userEntity = userRepository.findByUsername(username);

		if(userEntity==null) { //최초 로그인 시
			User user = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.name(name)
					.role("ROLE_USER")
					.build();
			return new PrincipalDetail(userRepository.save(user));
		}else {
			return new PrincipalDetail(userEntity);
		}
	}
}
