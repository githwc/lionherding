package com.lh.common.encoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 功能描述：AES 加密算法
 *
 * <pre>
 * 算法说明：
 *     AES，全称为“Advanced Encryption Standard”，中文名“高级加密标准”，
 * 在密码学中又称 Rijndael 加密法，是美国联邦政府采用的一种区块加密标准。AES 加
 * 密算法作为新一代的数据加密标准汇聚了强安全性、高性能、高效率、易用和灵活等优点。
 * AES 设计有三个密钥长度：128，192，256 位。相对而言，AES 的 128 密钥比
 * DES 的 56 密钥强了 1021 倍。
 * </pre>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-05-09
 */
public class AES {

	/**
	 * AES 对称加密
	 * @param content	: 未加密的内容
	 * @param key		: 秘钥
	 * @return 加密结果
	 */
	public static String encrypt(String content, String key) {

		if ((content == null) || (key == null)) {
			return null;
		}

		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(1, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
			byte[] bytes = cipher.doFinal(content.getBytes("utf-8"));
			return new BASE64Encoder().encode(bytes).replaceAll("\\s", "");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * AES 对称解密
	 * @param content	: 加密后的内容
	 * @param key		: 秘钥
	 * @return 解密结果
	 */
	public static String decrypt(String content, String key) {

		if ((content == null) || (key == null)) {
			return null;
		}

		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(2, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
			byte[] bytes = new BASE64Decoder().decodeBuffer(content);
			bytes = cipher.doFinal(bytes);
			return new String(bytes, "utf-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return null;
	}

}
