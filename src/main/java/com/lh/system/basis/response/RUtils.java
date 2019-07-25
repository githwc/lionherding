package com.lh.system.basis.response;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-07-23 09:25
 * @Version: 1.0.0
 */
public class RUtils {

    public static String formatMsg(String msg, Object... params) {
        int i = 0, j;
        StringBuilder content = new StringBuilder(msg.length() + 50);
        for (Object param : params) {
            j = msg.indexOf("{}", i);
            // 找到{}等待替换
            if (j != -1) {
                content.append(msg, i, j).append(param);
                i = j + 2;
            } else {
                break;
            }
        }
        content.append(msg, i, msg.length());
        return content.toString();
    }
}
