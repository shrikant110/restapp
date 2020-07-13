package com.cardreader.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.cardreader.config.util.CommonUtils;
/**
 * 
 * @author shrikant.kushwaha
 *
 */
@Component("applicationLogOutSuccessHandler")
public class ApplicationLogOutSuccessHandler implements LogoutSuccessHandler{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		CommonUtils.prepareErrorResponse(request, response, 200, "Logged out successfully.", false);
	}
	
}
