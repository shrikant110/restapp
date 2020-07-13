package com.cardreader.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import com.cardreader.config.util.CommonUtils;
import com.cardreader.config.util.HttpStatusCodes;
import com.cardreader.entities.User;
import com.cardreader.services.UserService;
/**
 * 
 * @author shrikant.kushwaha
 *
 */
@Component("applicationAuthenticationSuccessHandler")
public class ApplicationAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	
	@Autowired
	UserService userService;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		System.out.println("==login successfully"+authentication.getPrincipal());
		User user = userService.find(authentication.getName());
		if(user.getStatus().getStatusId()==1) {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			CommonUtils.prepareSuccessResponse(request, response, HttpStatusCodes.LOGINSUCCESS.getCode(), "Login Successful.", false,user);
		}else {
			CommonUtils.prepareSuccessResponse(request, response, HttpStatusCodes.ACCOUNTINACTIVE.getCode(), "Account Inactive.", false,null);
		}
		clearAuthenticationAttributes(request);
	}

	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

}