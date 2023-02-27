package com.likelion.cheg.config.auth;

import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService{
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if(userEntity==null){
			System.out.println("UserEntity가 null임");
			throw new UsernameNotFoundException(username);

		}
		else
			return new PrincipalDetail(userEntity);
	}
}
