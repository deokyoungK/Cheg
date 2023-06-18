package com.likelion.cheg.config;

import com.likelion.cheg.config.handler.CustomAuthFailureHandler;
import com.likelion.cheg.config.oauth.session.OAuth2DetailsService;
import com.likelion.cheg.config.oauth.token.CustomOAuth2UserService;
import com.likelion.cheg.config.oauth.token.JwtAuthFilter;
import com.likelion.cheg.config.oauth.token.OAuth2SuccessHandler;
import com.likelion.cheg.config.oauth.token.TokenService;
import com.likelion.cheg.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@RequiredArgsConstructor
@EnableWebSecurity // 해당파일로 시큐리티를 활성화 -> 기본 스프링 필터체인에 등록
@Configuration //IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final OAuth2DetailsService oAuth2DetailsService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Bean
    public HttpFirewall defaultHttpFirewall(){
        return new DefaultHttpFirewall();
    }

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler(){
        return new CustomAuthFailureHandler();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.httpFirewall(defaultHttpFirewall());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
        http.csrf().disable();
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/detail/**","/user/**","/cart/**","/mypage/**","/detailPayment/**","/cartPayment/**","/admin/**").hasRole("USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().permitAll();

        http.formLogin() //폼 로그인 진행
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/login")
                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler()) //로그인 성공 후 이전페이지로 리다이렉트
                .failureHandler(failureHandler()); // 에러 발생 시 핸들러 지정

        http.oauth2Login() //oauth2로그인도 추가로 진행
                .userInfoEndpoint().userService(oAuth2DetailsService)//accessToken으로 사용자 정보 받아오기
                .and()
//                .successHandler(oAuth2SuccessHandler) //사용자에게 jwt토큰 발급하기
                .permitAll();


//        http.addFilterBefore(new JwtAuthFilter(tokenService,userRepository), UsernamePasswordAuthenticationFilter.class);
    }


}
