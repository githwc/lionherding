package org.framework.core.encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * 功能描述：信息摘要算法, 简称：MD5(Message Digest algorithm 5)
 * 
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @Company  LionHerding
 * @Author   牧狮&&紫色年华
 * @Datetime 2016-05-09
 */
public class MD5 {

	/**
	 * 字符串转MD5
     * @param text ：文本内容
	 * @return　加密后的内容
	 */
	public static String encrypt(String text) { 
		String result = null;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			result = byte2hex(md.digest(text.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

    /**
     * 加密辅助
     * @param b : 内容二进制数组
     * @return 加密大写十六进制
     */
    private static String byte2hex(byte[] b) {
        String hs = "", stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * @Description:MD5加解密 执行一次加密，两次解密
     * @Date: 14:19 2019/5/10
     * @Param:
     * @Return:
     */
    public static String convertMD5(String inStr){
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }
	
}
