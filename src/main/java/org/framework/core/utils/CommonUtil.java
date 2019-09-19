package org.framework.core.utils;

import java.util.UUID;

/**
 * 功能描述：公共工具类
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-05-09
 */
public class CommonUtil {

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


}
