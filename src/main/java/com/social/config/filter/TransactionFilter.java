package com.social.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;



@Component
public class TransactionFilter implements Filter {
 
    
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("=====>init" );
		
	}

	@Override
	public void doFilter(ServletRequest request, javax.servlet.ServletResponse response, FilterChain chain)	throws IOException, ServletException {
		System.out.println("=====>doFilter" );
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setDateHeader("Expires", 0);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("=====>System.out.println(\"=====>destroy\" );" );
		
	}
 
    // other methods 

}
