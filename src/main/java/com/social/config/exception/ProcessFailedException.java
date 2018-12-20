package com.social.config.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.NoSuchMessageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.social.config.exception.base.AbstractBaseException;
import com.social.config.util.ResponseMessageDTO;

@ControllerAdvice
public class ProcessFailedException extends AbstractBaseException{
	private static final long serialVersionUID = 3177413526753238654L;
	public ProcessFailedException() {
		super("Error generated due to some unknown reasons.");
	}
	
	public ProcessFailedException(String message) {
		super(message);
	}
	@ExceptionHandler({ProcessFailedException.class,NullPointerException.class,NoSuchMessageException.class})
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
