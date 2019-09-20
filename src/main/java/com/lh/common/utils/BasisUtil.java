package com.lh.common.utils;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述：基础工具类
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-20
 * @Version: 1.0.0
 */
public class BasisUtil {

    /**
     * @Description:生成UUID
     * @Date: 15:11 2019/5/10
     * @Param:
     * @Return:
     * synchronized:当用它来修饰一个方法或者一个代码块的时候，可以保证在同一时刻最多仅仅有一个线程运行该段代码。
     */
    public static synchronized String getUUID() {
        return (UUID.randomUUID().toString()).replace("-", "");
    }

    /**
     * 判断为空
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if ("null".equals(object)) {
            return (true);
        }
        return (false);
    }

    /**
     * 判断不为空
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Object object) {
        if (object != null && !object.equals("") && !object.equals("null")) {
            return (true);
        }
        return (false);
    }

    /**
     * 生成随机数
     * @param place 定义随机数的位数
     */
    public static String randomGen(int place) {
        String base = "qwertyuioplkjhgfdsazxcvbnmQAZWSXEDCRFVTGBYHNUJMIKLOP0123456789";
        StringBuffer sb = new StringBuffer();
        Random rd = new Random();
        for(int i=0;i<place;i++) {
            sb.append(base.charAt(rd.nextInt(base.length())));
        }
        return sb.toString();
    }

    /**
     * java去除字符串中的空格、回车、换行符、制表符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * @Description:把字符串数组用指定的连接符连成字符串
     * @Date: 17:22 2019/5/9
     * @Param: array:数组; link:连接符
     * @Return:串联之后的字符串
     */
    public static String join(String[] array, String link) {
        if (array == null || array.length == 0) return "";
        StringBuffer result = new StringBuffer(array[0]);
        for (int i = 1; i < array.length; i++) {
            result.append(link + array[i]);
        }
        return result.toString();
    }
}
