package com.lh.common.encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * 功能描述：三重数据加密算法，简称：3DES(Three Data Encryption Standard)
 * <pre>
 * 算法说明：
 *     3DES相当于是对每个数据块应用三次 DES 加密算法。由于计算机运算能力的增强，
 * 原版 DES 密码的密钥长度变得容易被暴力破解；3DES 即是设计用来提供一种相对简单的
 * 方法，即通过增加 DES的密钥长度来避免类似的攻击，而不是设计一种全新的块密码算法。
 * </pre>
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company  紫色年华
 * @Author   xieyc
 * @Datetime 2016-05-09
 */
public class ThreeDES {

	//定义 加密算法, 可用 DES, DESede, Blowfish
	private static final String Algorithm = "DESede";

    /**
     * @param keybyte	: 加密密钥，长度为24字节
     * @param src		: 被加密的数据缓冲区（源）
     * @return 加密结果: byte[]
     */
    public static byte[] encrypt(byte[] keybyte, byte[] src) {
       try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * @param keybyte	: 加密密钥，长度为24字节
     * @param src		: 加密后的缓冲区
     * @return 解密结果: byte[]
     */
    public static byte[] decrypt(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);

			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
        return null;
    }

 }
