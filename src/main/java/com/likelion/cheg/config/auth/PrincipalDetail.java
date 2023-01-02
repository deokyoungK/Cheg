package com.likelion.cheg.config.auth;

import com.likelion.cheg.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


@Data
public class PrincipalDetail implements UserDetails,OAuth2User{

	private User user;
	private Map<String,Object> attributes;

	public PrincipalDetail(User user) {
		this.user = user;
	}


	//권한은 한개가 아닐 수 있다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{return user.getRole();});
		return collectors;
	}

	@Override
	public String getPassword() { return user.getPassword(); }

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getName() {
		return null;
	}


}
