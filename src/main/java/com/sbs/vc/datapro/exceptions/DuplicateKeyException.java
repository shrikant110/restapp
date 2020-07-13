/**
 * 
 */
package com.sbs.vc.datapro.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sbs.vc.config.util.ResponseMessageDTO;
import com.sbs.vc.datapro.exceptions.base.AbstractBaseException;

/**
 * @author santosh.panigrahi
 *
 */
@ControllerAdvice
public class DuplicateKeyException extends AbstractBaseException{

	
	private static final long serialVersionUID = -3397601627237806759L;
	public DuplicateKeyException() {
		super();
	}
	
	public DuplicateKeyException(String message) {
		super(message);
	}
	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}

}
