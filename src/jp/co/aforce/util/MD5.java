package jp.co.aforce.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {


	public String doHash(String id, String password) {

		String hashId = hash(id);
		String hashPassword = hash(password);
		String salt = "CKLMzAsFP4yMS9N\r\nRT9EZAPuyCkACTK\r\n";

		return hash(hashId + hashPassword + salt);

	}

	public String hash(String str) {

		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] md5_result = md5.digest(str.getBytes());

		return String.format("%020x", new BigInteger(1, md5_result));
	}

}
