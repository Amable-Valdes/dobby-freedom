package uo.sdi.business.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import uo.sdi.business.util.BusinessException;

public class Encriptator {

	public static String encrypt(String password) throws BusinessException {
		try {

			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes());
			StringBuffer passwordEncrypted = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				passwordEncrypted.append(Integer.toString(
						(hash[i] & 0xff) + 0x100, 16).substring(1));
			}

			return passwordEncrypted.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new BusinessException(e);
		}
	}
}
