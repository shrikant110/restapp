package com.cardreader.config.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.cardreader.config.exception.ProcessFailedException;

/**
 * This program shows how AES encryption and decryption can be done in Java.
 * SecretKey is generated first time and store in to the server which are used for next time for encryption and decryption process.
 * @author Shrikant kushwaha
 */

@PropertySources({
       @PropertySource("classpath:application_env.properties")
})
public class AESEncryption {
	

 
    /**
     * 1. Generate a plain text for encryption
     * 2. Get a secret key (printed in hexadecimal form). In actual use this must 
     * by encrypted and kept safe. The same key is required for decryption.
     * 3. 
     */
	public static final String SECURE_KEY_PATH="/home/ec2-user/aurora/certification/auro/api.ser";
       
    public static String encryptText(String plainText)  throws ProcessFailedException{
    	String encryText=null;
    	try {
			SecretKey secKey = getSavedSecreatKey();
			byte[] cipherText = encryptText(plainText, secKey);
			encryText = DatatypeConverter.printBase64Binary(cipherText);
		} catch (Exception e) {
			throw new ProcessFailedException ("Encrption Issue :"+e.getMessage());
		}
		return encryText;
    }
    public static String decryptText(String encyptText) throws Exception{
    	SecretKey secKey=getSavedSecreatKey();
    	String decryptedText = decryptText(DatatypeConverter.parseBase64Binary(encyptText), secKey);
    	return decryptedText;
    }
    
    
    
    public static SecretKey createSecurityKey() throws IOException, NoSuchAlgorithmException{
    	SecretKey secKey = getSecretEncryptionKey();
    	FileOutputStream fout = new FileOutputStream(SECURE_KEY_PATH);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(secKey);
        return secKey;
    }
    
    
    public static SecretKey getSavedSecreatKey() throws NoSuchAlgorithmException, IOException, ClassNotFoundException {
    	SecretKey secKey;
		try {
			FileInputStream fin = new FileInputStream(SECURE_KEY_PATH);
			ObjectInputStream ois = new ObjectInputStream(fin);
			secKey = (SecretKey) ois.readObject();
		} catch (FileNotFoundException e) {
			return createSecurityKey();
		}
        return secKey;
    }
    
    /**
     * gets the AES encryption key. In your actual programs, this should be safely
     * stored.
     * @return
     * @throws NoSuchAlgorithmException 
     * @throws Exception 
     */
    public static SecretKey getSecretEncryptionKey() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128); // The AES key size in number of bits
        SecretKey secKey = generator.generateKey();
        return secKey;
    }
    
    /**
     * Encrypts plainText in AES using the secret key
     * @param plainText
     * @param secKey
     * @return
     * @throws InvalidKeyException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws Exception 
     */
    public static byte[] encryptText(String plainText,SecretKey secKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
        return byteCipherText;
    }
    
    /**
     * Decrypts encrypted byte array using the key used for encryption.
     * @param byteCipherText
     * @param secKey
     * @return
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws Exception 
     */
    public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        return new String(bytePlainText);
    }
    
    /**
     * Convert a binary byte array into readable hex form
     * @param hash
     * @return 
     */
    private static String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
}