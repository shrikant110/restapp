package com.social.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;


@Component("applicationAuthenticationFailureHandler")
public class ApplicationAuthenticationFailureHandler implements AuthenticationFailureHandler{ 
//extends ExceptionMappingAuthenticationFailureHandler{
	 

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		 
		System.out.println(" User Name "+request.getParameter("username"));
		System.out.println(" Password "+request.getParameter("password"));
		System.out.println("Exception (onAuthenticationFailure):"+exception);
		/*
		 if(exception instanceof  AccountExpiredException){
			CommonUtils.prepareErrorResponse(request, response, HttpStatusCodes.USERACCOUNTEXPIRED.getCode(), exception.getMessage(), true);
		 }else{
			 CommonUtils.prepareErrorResponse(request, response, HttpStatusCodes.LOGINFAILED.getCode(), exception.getMessage(), true);
		 }
		
		
			//response.setContentType("application/json");
			
			JSONObject respJson      = new JSONObject();
			respJson.put(SecurityFilterResponse.RESPSTATUS.getResponseString(), SecurityFilterResponse.LOGIN_FAILURE.getResponseString());
			respJson.put("message", exception.getMessage());
			
			response.getWriter().print(respJson.toString());
		    response.getWriter().flush();*/
	}
	
	
	
}
