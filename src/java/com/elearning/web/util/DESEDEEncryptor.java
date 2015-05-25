package com.elearning.web.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class DESEDEEncryptor {

	static Cipher ecipher;

	static Cipher dcipher;

	// PBKey length should be 24 at least
	private static String PBKey = "GuoXinShiJi201408042117_test_11";// "Qujsdgwegw/wegwesOOOOKKKKvxSSSS";

	public DESEDEEncryptor(String PBKey) throws Exception {

		KeySpec ks = new DESedeKeySpec(PBKey.getBytes("UTF8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		SecretKey key = keyFactory.generateSecret(ks);
		ecipher = Cipher.getInstance("DESede");
		dcipher = Cipher.getInstance("DESede");
		ecipher.init(Cipher.ENCRYPT_MODE, key);
		dcipher.init(Cipher.DECRYPT_MODE, key);
	}

	public DESEDEEncryptor() throws Exception {

		new DESEDEEncryptor(PBKey);
	}
	
	private void init(){
		
	}

	public String encrypt(String input) {

		try {
			// Encode the string into bytes using utf-8
			byte[] utf8 = input.getBytes("UTF8");

			// Encrypt
			byte[] enc = ecipher.doFinal(utf8);

			// 关键就在这了，要是直接把byte转成string会出现乱码，因为加密后的byte数组有的不在ASCC编码中，所以用了BigInteger
			return new BigInteger(1, enc).toString(16);
			// return new sun.misc.BASE64Encoder().encodeBuffer(enc);
		} catch (javax.crypto.BadPaddingException e) {} catch (IllegalBlockSizeException e) {} catch (UnsupportedEncodingException e) {} catch (java.io.IOException e) {}
		return null;
	}

	public String decrypt(String input) {

		try {
			// Decode base64 to get bytes
			// byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(input);

			byte[] dec = hexToBytes(input.toCharArray());
			// Decrypt
			byte[] utf8 = dcipher.doFinal(dec);
			// Decode using utf-8
			return new String(utf8, "UTF8");
		} catch (javax.crypto.BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private byte[] hexToBytes(char[] hex) {

		int length = hex.length / 2;
		byte[] raw = new byte[length];
		for (int i = 0; i < length; i++) {
			int high = Character.digit(hex[i * 2], 16);
			int low = Character.digit(hex[i * 2 + 1], 16);
			int value = (high << 4) | low;
			if (value > 127)
				value -= 256;
			raw[i] = (byte) value;
		}
		return raw;
	}
}
