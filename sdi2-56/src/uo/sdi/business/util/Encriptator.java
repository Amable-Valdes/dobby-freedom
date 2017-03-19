package uo.sdi.business.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import uo.sdi.business.util.BusinessException;

/**
 * Clase de utilidad que permite encriptar un String.
 * 
 * @author Amable y Carlos
 *
 */
public class Encriptator {

	/**
	 * Método estatico que permite encriptar un String.
	 * 
	 * @param password	El String a encriptar.
	 * @return	Un String ya encriptado.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
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
