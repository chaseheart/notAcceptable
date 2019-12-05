package com.isolver.common.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isolver.common.util.UserWithSalt;
import com.isolver.dao.user.UserRepository;
import com.isolver.entity.User;

/**
 * spring security 登录用service
 * @author IS1907006
 *
 */
@Service
public class MyUserDetailService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.debug("Load user by loginId: " + userName);
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		User user = userRepository.findByWorkId(userName);
		if (user == null) {
			logger.error("User not found");
			throw new UsernameNotFoundException("User not found");
		}

		authorities.add(new SimpleGrantedAuthority(user.getRole().getId().toString()));
		return new UserWithSalt(user.getWorkId(), user.getWorkId(), user.getPassword(), authorities, user.getId());
	}

}
