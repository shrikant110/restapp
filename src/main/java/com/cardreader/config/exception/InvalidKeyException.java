package com.cardreader.config.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cardreader.config.exception.base.AbstractBaseException;
import com.cardreader.config.util.ResponseMessageDTO;

/**
 * 
 * @author Saurabh.Agarwal
 *
 */
@ControllerAdvice
public class InvalidKeyException extends AbstractBaseException{

	private static final long serialVersionUID = 2442626413144704509L;
	public InvalidKeyException() {
		super();
	}
	
	public InvalidKeyException(String message) {
		super(message);
	}
	@ExceptionHandler(InvalidKeyException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
