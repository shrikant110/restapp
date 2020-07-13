package com.sbs.vc.config.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class BIOSEncryption {
	
	public static String encrypt(String cyperText) {
		Encoder encoder = Base64.getEncoder();
		String encodedString = encoder.encodeToString((cyperText).getBytes());
		return encodedString;
	}
	
	public static String dncrypt(String encodeText) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedByte = decoder.decode(encodeText);
		String decodedString = new String(decodedByte);
		return decodedString;
	}
	

}
