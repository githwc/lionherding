package com.lh.common.encoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * 功能描述：BASE64 严格地说，属于编码格式，而非加密算法
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company  紫色年华
 * @Author   xieyc
 * @Datetime 2019-05-09
 */
public class BASE64 {

	/**
	 * 加密原文
	 * @param key: 原文
	 * @return 加密结果
	 * @throws Exception
	 */
	public static String encrypt(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * 加密密文
	 * @param key : 密文
	 * @return 解密结果
	 * @throws Exception
	 */
	public static byte[] decrypt(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

}
