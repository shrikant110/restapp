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
public class InvalidFileException  extends AbstractBaseException{

	
	private static final long serialVersionUID = -2196223513515386158L;

	public InvalidFileException() {
		super();
	}
	
	public InvalidFileException(String message) {
		super(message);
	}
	@ExceptionHandler(InvalidFileException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
