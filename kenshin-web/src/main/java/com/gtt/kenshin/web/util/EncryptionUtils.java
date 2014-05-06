package com.gtt.kenshin.web.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static org.apache.commons.lang3.StringUtils.*;


/**
 * 加密/解密工具
 *
 * @author tiantian.gao
 */
public abstract class EncryptionUtils {

	private static final String ENCODING_KEY = "ASCII";
	private static final String ENCODING_TEXT = "UTF-8";

	/**
	 * 区别AES算法对空格字符的混淆
	 */
	private static final String SPACE_INSTEAD = "~$#!^";
	private static final String SPACE2_INSTEAD = "^#~$!";
	private static final String SPACE2 = String.valueOf((char) 0);
	private static final String SPACELIST = " " + SPACE2;

	private static String encryptKey;
	private static String encryptIv;

	public static String encrypt(String text) {
		// 空处理
		if (isBlank(text)) {
			return EMPTY;
		}
		text = text.replace(" ", SPACE_INSTEAD).replace(SPACE2, SPACE2_INSTEAD);
		try {
			byte[] bytes = text.getBytes(ENCODING_TEXT);
			byte[] encryptedBytes =
					encrypt(bytes, getEncryptKey().getBytes(ENCODING_KEY), getEncryptIV().getBytes(ENCODING_KEY));
			if (!ArrayUtils.isEmpty(encryptedBytes)) {
				return parseByte2Hex(encryptedBytes);
			}
		} catch (Exception e) {
		}
		return EMPTY;
	}

	public static String decrypt(String cipherText) {
		// 空处理
		if (isBlank(cipherText)) {
			return EMPTY;
		}
		try {
			byte[] cipherBytes = parseHex2Byte(cipherText);
			byte[] decryptedBytes =
					decrypt(cipherBytes, getEncryptKey().getBytes(ENCODING_KEY), getEncryptIV().getBytes(ENCODING_KEY));
			if (!ArrayUtils.isEmpty(decryptedBytes)) {
				String decrypted = new String(decryptedBytes, ENCODING_TEXT);
				return strip(decrypted, SPACELIST).replace(SPACE_INSTEAD, " ").replace(SPACE2_INSTEAD, SPACE2);
			}
		} catch (Exception e) {
		}
		return EMPTY;
	}


	/**
	 * 使用AES算法,使用指定key和IV对指定字节数组进行加密
	 */
	private static byte[] encrypt(byte[] bytes, byte[] key, byte[] iv) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec KeySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, KeySpec, new IvParameterSpec(iv));
			return cipher.doFinal(padWithZeros(bytes));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 使用AES算法，使用指定key和IV对指定字节数组进行解密
	 */
	private static byte[] decrypt(byte[] bytes, byte[] key, byte[] iv) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec KeySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, KeySpec, new IvParameterSpec(iv));
			return cipher.doFinal(bytes);
		} catch (Exception e) {
			return null;
		}
	}

	private static byte[] padWithZeros(byte[] input) {
		int rest = input.length % 16;
		if (rest > 0) {
			byte[] result = new byte[input.length + (16 - rest)];
			System.arraycopy(input, 0, result, 0, input.length);
			return result;
		}
		return input;
	}

	private static String parseByte2Hex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toLowerCase());
		}
		return sb.toString();
	}

	private static byte[] parseHex2Byte(String hexText) {
		if (isEmpty(hexText)) {
			return null;
		}
		byte[] result = new byte[hexText.length() / 2];
		for (int i = 0; i < hexText.length() / 2; i++) {
			int high = Integer.parseInt(hexText.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexText.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 返回md5加密串
	 *
	 * @param plainText 加密文本
	 * @param needShort 是否返回16位，否则返回32位
	 * @param uppercase 是否返回大写形式
	 * @return
	 */
	public static String md5Hex(String plainText, boolean needShort, boolean uppercase) {
		String encrypted = DigestUtils.md5Hex(plainText);
		if (needShort) {
			encrypted = encrypted.substring(8, 24);
		}
		if (uppercase) {
			encrypted = encrypted.toUpperCase();
		}
		return encrypted;
	}

	private static String getEncryptKey() {
		return encryptKey;
	}

	private static String getEncryptIV() {
		return encryptIv;
	}


	public static void setEncryptKey(String key) {
		encryptKey = key;
	}

	public static void setEncryptIV(String iv) {
		encryptIv = iv;
	}

}
