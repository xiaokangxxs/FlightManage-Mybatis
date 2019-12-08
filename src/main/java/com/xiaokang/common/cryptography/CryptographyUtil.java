package com.xiaokang.common.cryptography;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class CryptographyUtil {

	/**
	 * 
	 * @Description: Base64加密
	 * @return String
	 * @param str
	 * @return
	 */
	public static String encBase64(String str) {
		return Base64.encodeToString(str.getBytes());
	}

	/**
	 * 
	 * @Description: Base64解密
	 * @return String
	 * @param str
	 * @return
	 */
	public static String decBase64(String str) {
		return Base64.decodeToString(str);
	}

	/**
	 * Md5加密(无敌加密)
	 * 
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String md5(String str, String salt) {
		return new Md5Hash(str, salt).toString();
	}

	@Test
	public void testDec() {
		/*
		 * String str = "123456"; String encBase64 = CryptographyUtil.encBase64(str);
		 * String decBase64 = CryptographyUtil.decBase64(encBase64);
		 * System.out.println(encBase64 + "-" + decBase64);
		 */
		String pwd = "123456";
		String md5 = CryptographyUtil.md5(pwd, CryptographyUtil.md5("www.xiaokang.cool", "xkxxs"));
		System.out.println(md5);
	}
}
