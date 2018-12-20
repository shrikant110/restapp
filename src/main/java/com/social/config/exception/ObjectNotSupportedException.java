package com.social.config.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.social.config.exception.base.AbstractBaseException;
import com.social.config.util.ResponseMessageDTO;


@ControllerAdvice
public class ObjectNotSupportedException extends AbstractBaseException{

	private static final long serialVersionUID = -7453416172206935310L;
	public ObjectNotSupportedException() {
		super();
	}
	
	public ObjectNotSupportedException(String message) {
		super(message);
	}
	@ExceptionHandler(ObjectNotSupportedException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}

}
