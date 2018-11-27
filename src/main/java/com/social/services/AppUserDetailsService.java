package com.social.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.social.entities.User;

/**
 * 
 * @author shrikant.kushwaha
 *
 */

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	Logger logger = LoggerFactory.getLogger(AppUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.find(username);
		logger.info("Login User Name===>"+username);
		logger.info("Login User ===>"+user);
		return  user;
	}

}
