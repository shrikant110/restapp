package com.cardreader.config.util.constant;

import java.io.File;


public enum ApplicationConstants {
	CONFIGURATIONSBUNDLE(File.separator + "configuration.properties"),
	EXCEPTIONBUNDLE(File.separator + "exception.properties");
		
	private String value;
	
	private ApplicationConstants(String value) {
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}
}
