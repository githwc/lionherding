package org.framework.core.utils;

import java.io.UnsupportedEncodingException;

/**
 * 功能描述：关于加解码的工具类
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-05-10 13:48
 */
public class EncoderUtil {

    /**
     * @Description:URL解码
     * @Date: 16:55 2019/5/9
     * @Param:   encoderVal:需要解码的参数
     * @Return:  正确解码的中文参数值
     */
    public static String getURLDecoderString(String encoderVal) {
        String result = "";
        if (null == encoderVal) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(encoderVal, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description:URL转码
     * @Date: 17:13 2019/5/9
     * @Param: decoderVal:需要转码的参数
     * @Return: 正确转码的参数值
     */
    public static String getURLEncoderString(String decoderVal) {
        String result = "";
        if (null == decoderVal) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(decoderVal, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description:编码转换,从UTF-8到GBK
     * @Date: 13:41 2019/5/10
     * @Param strVal:UTF-8字符
     * @Return: GBK字符
     */
    public static String UTF82GBK(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = strVal.trim();
                strVal = new String(strVal.getBytes("UTF-8"), "GBK");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * @Description:编码转换：从GBK到UTF8
     * @Date: 13:46 2019/5/10
     * @Param:
     * @Return:
     */
    public static String GBK2UTF8(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("GBK"), "UTF-8");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * @Description:编码转换,从“ISO8859_1”到“GBK”得到的字符串
     * @Date: 13:44 2019/5/10
     * @Param strVal 要转换的字符串
     * @Return:
     */
    public static String ISO2GBK(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = strVal.trim();
                strVal = new String(strVal.getBytes("ISO8859_1"), "GBK");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * @Description:编码转换：从“GBK”到“ISO8859_1”得到的字符串
     * @Date: 13:45 2019/5/10
     * @Param:
     * @Return:
     */
    public static String GBK2ISO(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("GBK"), "ISO8859_1");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * @Description:编码转换 从ISO到UTF8
     * @Date: 13:50 2019/5/10
     * @Param:
     * @Return:
     */
    public static String ISO2UTF8(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("ISO-8859-1"), "UTF-8");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * @Description:编码转换,从UTF8到ISO-8895-1
     * @Date: 13:51 2019/5/10
     * @Param:
     * @Return:
     */
    public static String UTF82ISO(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("UTF-8"), "ISO-8859-1");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }


}
