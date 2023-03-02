package com.likelion.cheg.config;

import com.likelion.cheg.config.oauth.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@RequiredArgsConstructor
@EnableWebSecurity // 해당파일로 시큐리티를 활성화, 자동으로 csrf 보호기능 활성화
@Configuration //IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final OAuth2DetailsService oAuth2DetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.httpFirewall(defaultHttpFirewall());
    }
    @Bean
    public HttpFirewall defaultHttpFirewall(){
        return new DefaultHttpFirewall();
    }

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //super삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**","/cart/**","/mypage/**").authenticated()
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/")
            .and()
                .oauth2Login() //oauth2로그인도 추가로 진행
                .userInfoEndpoint() //oauth2로그인 성공 후에 사용자 정보를 바로 가져온다.
                .userService(oAuth2DetailsService);
    }
}
