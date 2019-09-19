package org.framework.core.encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 *
 * 功能描述：信息摘要算法, 简称：MD5(Message Digest algorithm 5)
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company  紫色年华
 * @Author   xieyc
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

    /**
     * @Description:MD5加盐加密
     * @Date: 2019/6/13 10:33
     * @Param:
     * @Return:
     * @throws:
     */
    public static String getSaltMD5(String password) {
        // 生成一个16位的随机数
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        if (sBuilder.length() < 16) {
            for (int i = 0; i < 16 - sBuilder.length(); i++) {
                sBuilder.append("0");
            }
        }
        // 生成最终的加密盐
        String Salt = sBuilder.toString();
        password = encrypt(password + Salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = Salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }

    /**
     * @Description:验证加盐后是否和原文一致
     * @Date: 2019/6/13 10:42
     * @Param:
     * @Return:
     * @throws:
     */
    public static boolean compareStrAndSaltMD5(String password, String md5str) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5str.charAt(i);
            cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
            cs2[i / 3] = md5str.charAt(i + 1);
        }
        String Salt = new String(cs2);
        return encrypt(password + Salt).equals(String.valueOf(cs1));
    }
}
