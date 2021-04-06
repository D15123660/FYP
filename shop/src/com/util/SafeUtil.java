package com.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.util.Base64Utils;


/**
 * Security tools
 */
public class SafeUtil {
	
	/**
	 * md5 encrypted string
	 * @param str
	 * @return
	 */
	public final static String md5(String str){
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(str.getBytes());
		return Base64Utils.encodeToString(messageDigest.digest());
	}
	
	/**
	 * sha1 encrypted string
	 * @param str
	 * @return
	 */
	public final static String sha1(String str){
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(str.getBytes());
		return Base64Utils.encodeToString(messageDigest.digest());
	}

	/**
	 * Encrypt using a specific encryption paradigm
	 * @param str
	 * @return
	 */
	public final static String encode(String str){
		return md5(sha1(md5(str)));
	}
	
}
