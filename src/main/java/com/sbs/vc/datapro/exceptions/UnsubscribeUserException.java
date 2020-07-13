package com.sbs.vc.datapro.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sbs.vc.config.util.ResponseMessageDTO;
import com.sbs.vc.datapro.exceptions.base.AbstractBaseException;

@ControllerAdvice
public class UnsubscribeUserException extends AbstractBaseException{
	private static final long serialVersionUID = -7453416172206935310L;
	public UnsubscribeUserException() {
		super();
	}
	
	public UnsubscribeUserException(String message) {
		super(message);
	}
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
