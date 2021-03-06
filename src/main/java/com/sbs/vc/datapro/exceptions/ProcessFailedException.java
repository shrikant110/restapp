package com.sbs.vc.datapro.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.NoSuchMessageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sbs.vc.config.util.ResponseMessageDTO;
import com.sbs.vc.datapro.exceptions.base.AbstractBaseException;
/**
 * 
 * @author shri
 *
 */
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
