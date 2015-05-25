package com.elearning.web.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecretUtil {

	private static String byteHex(byte ib) {

		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
				'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];

		return new String(ob);
	}

	public static String MD5(String str) {

		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(str.getBytes(Charset.defaultCharset().toString()));

			byte[] b = md.digest();
			StringBuffer result = new StringBuffer();
			for (byte aB : b)
				result.append(byteHex(aB));
			return result.toString();
		} catch (Exception e) {
			return "";
		}
	}

	@SuppressWarnings("unused")
	private static String base64Decode(String inStr) {

		try {
			return new String(Base64.decodeBase64(inStr.getBytes()), Charset.defaultCharset()
					.toString());
			// return new String(decoder.decodeBuffer(inStr),
			// Charset.defaultCharset().toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static final String IV = "1234567-";

	public static final String KeyDESCBC = "guoxinshiji201408031807";

	/**
	 * DESCBC加密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static String encryptDESCBC(String src) throws Exception {

		DESKeySpec desKeySpec = new DESKeySpec(KeyDESCBC.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

		IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));

		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		byte[] b = cipher.doFinal(src.getBytes("UTF-8"));

		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(b);
	}

	/**
	 * DESCBC解密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static String decryptDESCBC(String src) throws Exception {

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytesrc = decoder.decodeBuffer(src);

		DESKeySpec desKeySpec = new DESKeySpec(KeyDESCBC.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

		IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));

		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		byte[] retByte = cipher.doFinal(bytesrc);

		return new String(retByte);
	}
}
