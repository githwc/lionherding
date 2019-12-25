package com.lh.common.utils;

import java.util.Random;
import java.util.UUID;

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

}
