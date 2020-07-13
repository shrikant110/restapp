package com.cardreader.config.exception.base;

import com.cardreader.config.exception.InvalidFileException;
import com.cardreader.config.exception.InvalidKeyException;


public interface IErrorCode {
	String getErrorCode() throws InvalidKeyException,InvalidFileException;
}
	