package com.sbs.vc.aspects;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Configuration
public class UserAccessAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controller() {
	}

	@Pointcut("execution(* *.*(..))") protected void allMethod() { }
	/*
	 * 
	 * @Before("execution(* com.aurora.*.*.*.*(..))") public void before(JoinPoint
	 * joinPoint){ System.out.println(">>Befour"); logger.info(" {} before =>",
	 * joinPoint); logger.info("Entering in Method :  " +
	 * joinPoint.getSignature().getName()); logger.info("Class Name :  " +
	 * joinPoint.getSignature().getDeclaringTypeName()); logger.info("Arguments :  "
	 * + Arrays.toString(joinPoint.getArgs())); logger.info("Target class : " +
	 * joinPoint.getTarget().getClass().getName());
	 * 
	 * }
	 * 
	 * @After("execution(* com.aurora.*.*.*.*(..))") public void after(JoinPoint
	 * joinPoint){ System.out.println(">>after"); logger.info(" {} after ==>",
	 * joinPoint); }
	 */
	
	//before -> Any resource annotated with @Controller annotation 
		//and all method and function taking HttpServletRequest as first parameter
		
	
	@Before("controller() && allMethod()")
		public void logBefore(JoinPoint joinPoint) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			System.out.println(">>Befour--");
			logger.info(" {} before =>", joinPoint);
			logger.info("Entering in Method :  " + joinPoint.getSignature().getName());
			logger.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
			logger.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
			logger.info("Target class : " + joinPoint.getTarget().getClass().getName());

			if (null != request) {
				logger.info("Start Header Section of request ");
			
				Enumeration headerNames = request.getHeaderNames();
				while (headerNames.hasMoreElements()) {
					String headerName = (String) headerNames.nextElement();
					String headerValue = request.getHeader(headerName);
					logger.info("Header Name: " + headerName + " Header Value : " + headerValue);
				}
				logger.info("Request Path info :" + request.getServletPath());
				logger.info("Method Type : " + request.getMethod());
				logger.info("Access From :" + request.getRemoteHost());
				logger.info("End Header Section of request ");
			}
			
		}
		
		//Around -> Any method within resource annotated with @Controller annotation 
		@Around("controller() && allMethod()")
		public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
			
			long start = System.currentTimeMillis();
			try {
				String className = joinPoint.getSignature().getDeclaringTypeName();
				String methodName = joinPoint.getSignature().getName();
				Object result = joinPoint.proceed();
				long elapsedTime = System.currentTimeMillis() - start;
				logger.debug("Method " + className + "." + methodName + " ()" + " execution time : "+ elapsedTime + " ms");
			
				return result;
			} catch (IllegalArgumentException e) {
				logger.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "+ joinPoint.getSignature().getName() + "()");
				throw e;
			}
		}
		
		//After -> All method within resource annotated with @Controller annotation 
		// and return a  value
		@AfterReturning(pointcut = "controller() && allMethod()", returning = "result")
		public void logAfter(JoinPoint joinPoint, Object result) {
			String returnValue = this.getValue(result);
			logger.debug("Method Return value : " + returnValue);
		}
		
		
		@AfterThrowing(pointcut = "controller() && allMethod()", throwing = "exception")
		public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
			logger.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
			logger.error("Cause : " + exception.getCause());
		}
		
		
		private String getValue(Object result) {
			String returnValue = null;
			if (null != result) {
				if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
					returnValue = ReflectionToStringBuilder.toString(result);
				} else {
					returnValue = result.toString();
				}
			}
			return returnValue;
		} 

}