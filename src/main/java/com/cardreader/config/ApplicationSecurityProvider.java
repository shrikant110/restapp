package com.cardreader.config;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 
 * @author shrikant.kushwaha
 *
 */

@Component("applicationSecurityProvider")
public class ApplicationSecurityProvider {

	/**
	 * Retunrs logged in user name from Spring Security Context
	 * @return
	 */
	public String getLoggedInUserName(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User user = null;
		String userId = null;
		if(!(authentication instanceof AnonymousAuthenticationToken )){
			user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(user!=null){
				userId = user.getUsername();
			}
		}
		
		return userId;
	}

	
	/**
	 * Hashing for passwords. 'Bcrypt', SHA256 are supported
	 * @param algo ('bcrypt' or 'sha256')
	 * @param rawText
	 * @return
	 */
	public static String encode(String algo, String rawText){
		
		String hashedCode = rawText;
		if(algo.equalsIgnoreCase("bcrypt")){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			hashedCode = encoder.encode(rawText);
		}else if(algo.equalsIgnoreCase("sha256")){
			StandardPasswordEncoder  encoder = new StandardPasswordEncoder();
			hashedCode = encoder.encode(rawText);
		}else{
			hashedCode = NoOpPasswordEncoder.getInstance().encode(rawText);
		}
		
		return hashedCode;
		
	}
	
	/**
	 * Verifying passwords. 'Bcrypt', SHA256 are supported
	 * @param algo ('bcrypt' or 'sha256')
	 * @param encodedPassword
	 * @param rawText
	 * @return
	 */
	public static boolean matches(String algo, String rawText, String encodedPassword){
		
		boolean passwordMatch= false;
		if(algo.equalsIgnoreCase("bcrypt")){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			passwordMatch = encoder.matches(rawText, encodedPassword);
		}else if(algo.equalsIgnoreCase("sha256")){
			StandardPasswordEncoder  encoder = new StandardPasswordEncoder();
			passwordMatch = encoder.matches(rawText, encodedPassword);
		}else{
			passwordMatch = NoOpPasswordEncoder.getInstance().matches(rawText, encodedPassword);
		}
		
		return passwordMatch;
		
	}
}
