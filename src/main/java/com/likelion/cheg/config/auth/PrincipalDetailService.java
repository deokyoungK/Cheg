package com.likelion.cheg.config.auth;

import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService{
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if(userEntity==null){
			log.info("User가 null");
			throw new UsernameNotFoundException(username);

		}
		else
			return new PrincipalDetail(userEntity);
	}
}
