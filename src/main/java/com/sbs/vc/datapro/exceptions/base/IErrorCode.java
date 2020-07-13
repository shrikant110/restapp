package com.sbs.vc.datapro.exceptions.base;

import com.sbs.vc.datapro.exceptions.InvalidFileException;
import com.sbs.vc.datapro.exceptions.InvalidKeyException;


public interface IErrorCode {
	String getErrorCode() throws InvalidKeyException,InvalidFileException;
}
	