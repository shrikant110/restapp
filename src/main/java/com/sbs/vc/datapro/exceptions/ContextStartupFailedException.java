package com.sbs.vc.datapro.exceptions;

/**
 * 
 * @author shri
 *
 */
public class ContextStartupFailedException extends RuntimeException{

	private static final long serialVersionUID = 4712644549773221574L;
	public ContextStartupFailedException() {
		super();
	}
	
	public ContextStartupFailedException(String message) {
		super(message);
	}
}
