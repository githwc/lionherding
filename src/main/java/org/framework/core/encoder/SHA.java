package org.framework.core.encoder;

import java.security.MessageDigest;

/**
 * 
 * 功能描述：安全散列算法, 简称：SHA(Secure Hash Algorithm）
 * 
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @Company  LionHerding
 * @Author   牧狮&&紫色年华
 * @Datetime 2016-05-09
 */
public class SHA {

	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance("SHA");
		sha.update(data);

		return sha.digest();

	}
	
}
