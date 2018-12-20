package com.social.config.exception.base;

import com.social.config.exception.InvalidFileException;
import com.social.config.exception.InvalidKeyException;


public interface IErrorCode {
	String getErrorCode() throws InvalidKeyException,InvalidFileException;
}
	