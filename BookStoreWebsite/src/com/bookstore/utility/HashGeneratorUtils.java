package com.bookstore.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hash functions utility class.
 * 
 * @author www.codejava.net
 *
 */
public class HashGeneratorUtils {
	private static EntityManagerFactory entityManagerFactory;

	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
	}
	
	public HashGeneratorUtils() {

	}

	public static String generateMD5(String message) throws HashGenerationException {
		return hashString(message, "MD5");
	}

	public static String generateSHA1(String message) throws HashGenerationException {
		return hashString(message, "SHA-1");
	}

	public static String generateSHA256(String message) throws HashGenerationException {
		return hashString(message, "SHA-256");
	}

	private static String hashString(String message, String algorithm) throws HashGenerationException {

		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

			return convertByteArrayToHexString(hashedBytes);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			throw new HashGenerationException("Could not generate hash from String", ex);
		}
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}
	
	public void close() {
		if(entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
}
