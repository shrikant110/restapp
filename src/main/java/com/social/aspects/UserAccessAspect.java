package com.social.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserAccessAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* com.social.*.*.*(..))")
	public void before(JoinPoint joinPoint){
		logger.info(" {} before =>", joinPoint);
	}
	
	@After("execution(* com.social.*.*.*(..))")
	public void after(JoinPoint joinPoint){
		logger.info(" {} after ==>", joinPoint);
	}

}
